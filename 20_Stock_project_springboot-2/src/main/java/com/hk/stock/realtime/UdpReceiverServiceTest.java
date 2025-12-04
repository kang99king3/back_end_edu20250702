package com.hk.stock.realtime;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

//test용
//@Service
public class UdpReceiverServiceTest {

	private static final int PORT = 22101;

    @PostConstruct
    public void startUdp() {
        Thread t = new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(PORT)) {

                System.out.println("📡 UDP Listening on " + PORT);

                byte[] buf = new byte[2048];

                while (true) {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    String raw = new String(packet.getData(), 0, packet.getLength());

                    // STX, ETX 제거
//                    raw = raw.replace("\u0002", "").replace("\u0003", "");

                    System.out.println("RAW LEN = " + raw.length());
                    System.out.println("RAW DATA = " + raw);

                    // 🔥 substring 테스트
                    try {
                        String isin = raw.substring(25, 37).trim();
                        String priceStr = raw.substring(67, 78).trim();

                        String code = toStockCode(isin);
                        double price = parsePrice(priceStr);

                        System.out.println("ISIN = " + isin);
                        System.out.println("SYMBOL = " + code);
                        System.out.println("PRICE = " + price);
                        System.out.println("----------------------------------");

                    } catch (Exception e) {
                        System.out.println("❌ 파싱 오류: substring 범위 이상함");
                        System.out.println(e.getMessage());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t.setDaemon(true);
        t.start();
    }

    private double parsePrice(String s) {
        try { return Double.parseDouble(s.trim()); }
        catch (Exception e) { return Double.NaN; }
    }

    private String toStockCode(String isin) {
        if (isin == null) return null;
        isin = isin.trim();

        if (isin.startsWith("KR") && isin.length() >= 9) {
            return isin.substring(3, 9);
        }
        if (isin.matches("\\d{6}")) return isin;

        return null;
    }
}
