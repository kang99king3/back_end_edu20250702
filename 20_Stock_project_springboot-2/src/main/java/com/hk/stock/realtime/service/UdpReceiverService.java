package com.hk.stock.realtime.service;

import com.hk.stock.dto.PriceTickFull;
import com.hk.stock.realtime.UdpTickParser;
import com.hk.stock.realtime.store.TickRedisRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

//증권데이터 수신
@Service
@RequiredArgsConstructor
public class UdpReceiverService {

    private final TickRedisRepository redisRepo;

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
                    PriceTickFull tick = UdpTickParser.parse(raw);
//                    System.out.println(tick);
                    if (tick == null || tick.getSymbol() == null) continue;

                    redisRepo.saveTick(tick);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t.setDaemon(true);
        t.start();
    }
}

