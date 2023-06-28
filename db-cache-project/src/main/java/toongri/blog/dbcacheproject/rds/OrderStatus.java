package toongri.blog.dbcacheproject.rds;

import lombok.Getter;

@Getter
enum OrderStatus {

    ORDERED("ordered", "주문됨"),
    PAYED("payed", "결제됨"),
    RECEIVED("received", "수취됨"),
    REFUNDED("refunded", "환불됨"),
    CANCELED("canceled", "취소됨 ");

    private final String code;
    private final String text;

    OrderStatus(String code, String text) {
        this.code = code;
        this.text = text;
    }

}
