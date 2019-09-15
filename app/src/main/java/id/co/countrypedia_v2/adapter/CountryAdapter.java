package id.co.countrypedia_v2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.countrypedia_v2.R;
import id.co.countrypedia_v2.model.CountryModel;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {
    private List<CountryModel> data;

    public CountryAdapter(List<CountryModel> data) {
        this.data = data;
    }


    public void updateCountries(List<CountryModel> newCountries){
        data.clear();
        data.addAll(newCountries);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        holder.bind(data.get(position));
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_nation_flag)
        ImageView imgNationFlag;

        @BindView(R.id.tv_country)
        TextView tvCountry;

        @BindView(R.id.tv_capital)
        TextView tvCapital;


        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(CountryModel country) {
            tvCountry.setText(country.getCountryName());
            tvCapital.setText(country.getCapital());
        }
    }
}
