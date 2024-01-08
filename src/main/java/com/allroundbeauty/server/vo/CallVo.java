package com.allroundbeauty.server.vo;

import com.allroundbeauty.server.domain.Call;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class CallVo {
    private String source;
    private String destination;
    private double distance;
    private String arrivalTime;
    private String requirement;
    private int deliveryFee;

    private LocalDateTime convertArrivalTimeToDateTime(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a hh:mm");

        // "AM 12:00"과 같은 형식에 맞춰 문자열을 LocalTime으로 파싱
        LocalTime localTime = LocalTime.parse(string, formatter);

        // 현재 날짜와 합쳐서 LocalDateTime으로 생성
        return LocalDateTime.of(LocalDateTime.now().toLocalDate(), localTime);
    }
    public Call voToEntity(){
        return Call.builder().source(this.source).destination(this.destination).distance(this.distance)
                .arrivalTime(convertArrivalTimeToDateTime(this.arrivalTime)).requirement(this.requirement).deliveryFee(this.deliveryFee).build();
    }
}
