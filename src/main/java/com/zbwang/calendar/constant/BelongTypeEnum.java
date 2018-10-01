package com.zbwang.calendar.constant;

/**
 * Created by bobomeilin on 2018/8/19.
 */
public enum BelongTypeEnum {

    DESIGN("Design", "20"), HOUSE("Home", "21"), WE("We", "22"), VOICE("Voice", "0"), SEX("sex", "23"),
    BEAUTY("beauty", "24"), HOT("hot", "25"), HUOYING("huoying", "26"), OTHER("other", "100");

    BelongTypeEnum(String name, String type) {
        this.name = name;
        this.type = type;
    }

    private String name;

    private String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public static BelongTypeEnum getBelongTypeEnum(String belongType) {
        for (BelongTypeEnum belongTypeEnum : BelongTypeEnum.values()) {
            if (belongTypeEnum.getType().equals(belongType)) {
                return belongTypeEnum;
            }
        }
        return BelongTypeEnum.OTHER;
    }

    public boolean isSex(){
        return this == SEX || this == BEAUTY || this == HOT || this == HUOYING || this == OTHER;
    }

    @Override
    public String toString() {
        return "BelongTypeEnum{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
