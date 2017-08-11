package id.or.qodr.prayerstime.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.or.qodr.prayerstime.R;
import id.or.qodr.prayerstime.data.Datum;
import id.or.qodr.prayerstime.data.PrayersModel;

/**
 * Created by adul on 27/06/17.
 */

public class PrayersAdapter extends RecyclerView.Adapter<PrayersAdapter.PrayerViewHolder> {
    private List<Datum> prayers;
    private int rowLayout;
    private Context context;

    public PrayersAdapter(List<Datum> prayers, int rowLayout, Context context) {
        this.prayers = prayers;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PrayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PrayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrayerViewHolder holder, int position) {
        holder.txtTanggal.setText(prayers.get(position).getTanggal());
        holder.txtShubuh.setText(prayers.get(position).getShubuh());
        holder.txtTerbit.setText(prayers.get(position).getTerbit());
        holder.txtDzuhur.setText(prayers.get(position).getDzuhur());
        holder.txtAshr.setText(prayers.get(position).getAshr());
        holder.txtMaghrib.setText(prayers.get(position).getMaghrib());
        holder.txtIsya.setText(prayers.get(position).getIsya());
    }

    @Override
    public int getItemCount() {
        return prayers.size();
    }

    class PrayerViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTanggal,txtShubuh,txtTerbit,txtDzuhur,txtAshr,txtMaghrib,txtIsya;

        public PrayerViewHolder(View v) {
            super(v);
            txtTanggal = (TextView) v.findViewById(R.id.txt_tanggal);
            txtShubuh = (TextView) v.findViewById(R.id.txt_shubuh);
            txtTerbit = (TextView) v.findViewById(R.id.txt_terbit);
            txtDzuhur = (TextView) v.findViewById(R.id.txt_dzuhur);
            txtAshr = (TextView) v.findViewById(R.id.txt_ashr);
            txtMaghrib = (TextView) v.findViewById(R.id.txt_mgrib);
            txtIsya = (TextView) v.findViewById(R.id.txt_isya);
        }
    }
}
