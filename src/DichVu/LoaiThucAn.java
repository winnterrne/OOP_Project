package DichVu;

public enum LoaiThucAn {
    CHIEN,
    HAP,
    XAO,
    NUONG,
    NAU;

    public static LoaiThucAn tuTenMon(String tenMon) {
        tenMon = tenMon.toLowerCase();
        if(tenMon.contains("ran") || tenMon.contains("chien")) {
            return CHIEN;
        }else if(tenMon.contains("luoc") || tenMon.contains("hap")){
            return HAP;
        }else if(tenMon.contains("xao")) {
            return XAO;
        }else if(tenMon.contains("nuong") || tenMon.contains("nuong moi")) {
            return NUONG;
        }else if(tenMon.contains("sup") || tenMon.contains("canh")) {
            return NAU;
        }
        return NAU;
    }
}
