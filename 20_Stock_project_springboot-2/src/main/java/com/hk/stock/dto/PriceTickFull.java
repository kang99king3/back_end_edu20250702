package com.hk.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceTickFull {

    private String symbol;       // 6자리 종목코드
    private String isin;         // 12자리 원래 ISIN

    private String tradeTime;        // 거래시간 (1,6)
    private String tradeType;        // 거래종류 (7,1)
    private String dataType;         // 데이터구분값 (8,2)
    private String infoType;         // 정보구분값 (10,2)
    private String marketType;       // 시장구분 (12,1)
    private String seqNo;            // 정보분배일련번호 (13,8)
    private String boardId;          // 보드ID (21,2)
    private String sessionId;        // 세션ID (23,2)

    private String prodCodeRaw;      // 종목코드 Raw (25,12)
    private String tradeProcessTime; // 매매처리시각 (43,12)
    private double price;            // 체결가격 (67,11)
    private long volume;             // 거래량 (78,10)
    private long accVolume;          // 누적거래량 (121,12)
    private long accAmount;          // 누적거래대금 (133,22)
    private String bsType;           // 매수매도 구분 (155,1)

    private long ts;                 // 저장 UTC timestamp
    

	
}
