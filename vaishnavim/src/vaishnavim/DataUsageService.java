package vaishnavim;

public class DataUsageService {
    public void calculateCharge(DataUsageDTO dto) {
        double limit = 1.5; // daily data limit in GB
        if (dto.getDataUsed() > limit) {             dto.setExtraCharge((dto.getDataUsed() - limit) * 50);         } else {             dto.setExtraCharge(0);         }
        dto.setPaymentStatus("UNPAID");
    }
}

