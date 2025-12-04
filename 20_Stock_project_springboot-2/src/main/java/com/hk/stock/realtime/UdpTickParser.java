package com.hk.stock.realtime;

import com.hk.stock.dto.PriceTickFull;

//증권데이터를 받아 항목별로 추출해서 PriceTickFull에 맴버필드별로 저장
public class UdpTickParser {
    public static PriceTickFull parse(String raw) {
        try {
//            raw = raw.replace("\u0002", "").replace("\u0003", "");

            String tradeTime = sub(raw, 1, 6);
            String tradeType = sub(raw, 7, 1);
            String dataType = sub(raw, 8, 2);
            String infoType = sub(raw, 10, 2);
            String marketType = sub(raw, 12, 1);
            String seqNo = sub(raw, 13, 8);
            String boardId = sub(raw, 21, 2);
            String sessionId = sub(raw, 23, 2);
            String isin = sub(raw, 25, 12);
            String tradeProcessTime = sub(raw, 43, 12);

            double price = parseDouble(sub(raw, 67, 11));
            long volume = parseLong(sub(raw, 78, 10));
            long accVolume = parseLong(sub(raw, 121, 12));
            long accAmount = parseLong(sub(raw, 133, 22));
            String bsType = sub(raw, 155, 1);

            String symbol = extractSymbolFromIsin(isin);

            return PriceTickFull.builder()
                    .symbol(symbol)
                    .isin(isin)
                    .tradeTime(tradeTime)
                    .tradeType(tradeType)
                    .dataType(dataType)
                    .infoType(infoType)
                    .marketType(marketType)
                    .seqNo(seqNo)
                    .boardId(boardId)
                    .sessionId(sessionId)
                    .prodCodeRaw(isin)
                    .tradeProcessTime(tradeProcessTime)
                    .price(price)
                    .volume(volume)
                    .accVolume(accVolume)
                    .accAmount(accAmount)
                    .bsType(bsType)
                    .ts(System.currentTimeMillis())
                    .build();

        } catch (Exception e) {
            System.out.println("❌ UDP Parsing Error: " + e.getMessage());
            return null;
        }
    }

    private static String sub(String s, int pos, int len) {
        if (s.length() < pos + len) return "";
        return s.substring(pos, pos + len).trim();
    }

    private static double parseDouble(String s) {
        try { return Double.parseDouble(s); }
        catch (Exception e) { return Double.NaN; }
    }

    private static long parseLong(String s) {
        try { return Long.parseLong(s); }
        catch (Exception e) { return 0; }
    }

    private static String extractSymbolFromIsin(String isin) {
        if (isin.startsWith("KR") && isin.length() >= 9) {
            return isin.substring(3, 9);
        }
        return null;
    }
}
