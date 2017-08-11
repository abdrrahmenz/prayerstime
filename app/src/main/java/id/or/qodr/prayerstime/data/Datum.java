package id.or.qodr.prayerstime.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by adul on 29/06/17.
 */

public class Datum {
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("shubuh")
    @Expose
    private String shubuh;
    @SerializedName("terbit")
    @Expose
    private String terbit;
    @SerializedName("dzuhur")
    @Expose
    private String dzuhur;
    @SerializedName("ashr")
    @Expose
    private String ashr;
    @SerializedName("maghrib")
    @Expose
    private String maghrib;
    @SerializedName("isya")
    @Expose
    private String isya;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getShubuh() {
        return shubuh;
    }

    public void setShubuh(String shubuh) {
        this.shubuh = shubuh;
    }

    public String getTerbit() {
        return terbit;
    }

    public void setTerbit(String terbit) {
        this.terbit = terbit;
    }

    public String getDzuhur() {
        return dzuhur;
    }

    public void setDzuhur(String dzuhur) {
        this.dzuhur = dzuhur;
    }

    public String getAshr() {
        return ashr;
    }

    public void setAshr(String ashr) {
        this.ashr = ashr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsya() {
        return isya;
    }

    public void setIsya(String isya) {
        this.isya = isya;
    }
}
