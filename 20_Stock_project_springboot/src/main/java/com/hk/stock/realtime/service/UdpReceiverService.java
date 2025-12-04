package com.hk.stock.realtime.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.hk.stock.dto.PriceTick;
import com.hk.stock.realtime.store.TickStore;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UdpReceiverService {

    private final TickStore tickStore;
    private final SimpMessagingTemplate messaging;

    private static final int PORT = 22101;

    @PostConstruct
    public void start() {
        Thread t = new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(PORT)) {
                byte[] buf = new byte[2048];

                while (true) {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    String raw = new String(packet.getData(), 0, packet.getLength());
                    System.out.println(raw);
                    // TODO: 여기에 parse_record() 코드를 너가 알고 있는 포맷대로 구현
                    // ---- 예시 적용 ----
                    String isin = raw.substring(25, 37).trim();
                    String priceStr = raw.substring(67, 78).trim();

                    double price = 0;
                    try { price = Double.parseDouble(priceStr); } catch (Exception ignore) {}
//                    System.out.println(isin+":"+priceStr);
                    String symbol = toStockCode(isin);
                    
                    if (symbol == null) continue;

                    PriceTick tick = new PriceTick(symbol, price, System.currentTimeMillis());
                    tickStore.update(tick);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t.setDaemon(true);
        t.start();
    }

    private String toStockCode(String isin) {
        if (isin.startsWith("KR") && isin.length() >= 9) {
            return isin.substring(3, 9);
        }
        if (isin.matches("\\d{6}")) return isin;
        return null;
    }
}
