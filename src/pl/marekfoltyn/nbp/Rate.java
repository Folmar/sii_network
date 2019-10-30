package pl.marekfoltyn.nbp;

import java.math.BigDecimal;

//klasa POJO - nie ma innych metod poza getterami i setterami

public class Rate {
    private String currency;
    private String code;
    private BigDecimal mid;

    public Rate(String currency, String code, BigDecimal mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }

    public Rate() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }
}
