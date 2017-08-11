package id.or.qodr.prayerstime.api;

import android.content.Intent;

import id.or.qodr.prayerstime.data.PrayersModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by adul on 27/06/17.
 */

public interface ApiInterface {
    @GET("process/api/{apiKey}/jadwal-sholat")
    Call<PrayersModel> getDataPrayers(
            @Path("apiKey") String apiKey,
            @Query("idk") Integer idKota,
            @Query("bln") Integer bulan,
            @Query("thn") Integer tahun
    );
//
//    @GET("api/kurs")
//    Call<Models> getBank();
}
