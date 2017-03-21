package com.riclage.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MapsAdapter adapter = new MapsAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Route single = new Route(
            "cmeiHmmjMKfA[pBMh@H\\R`B\\|DBZMt@|Ap@VLf@oBzAuFlBaH\\{ALa@NMXu@\\qAlBwGzBkIjBqHxAiFdAaELq@Lc@P_@JKd@o@RWROLE`@C`DAjDEb@KVKZa@~AwC|B{DxCcFP]j@i@\\[h@c@NMPQv@o@z@}@pAyAdAyA|AcCrBiDNg@Lk@NiANk@Ti@tB{C@EbCsDdF}GdEcFjHmI|EkGz@_AhB{AvBsClBkCJMvByCn@cAJW\\w@`DuElMgQjEgGjA_Bl@m@PGHCt@g@|Ay@XQNMl@c@l@m@d@m@bAmBFOBSBWJkBBo@Tw@PYRMPCFCJ?NFTPLJJDLj@bD`N^|AhBdHnAnD~AlDzCrG`KpRXj@n@|Aj@nBn@nChMvj@fHxZdAxC~@jC?R@TZbBTtBF|C?x@K`CCnAAf@ElBGrES|FBhAHj@FTN^`@h@bCdBlIfGz@z@h@t@t@lAr@vAj@tA|BvFhAbCz@zAz@jAn@t@x@v@|AnA~AxA|GbHbD`DlItHpBbBbAn@`Bp@|FnAzCh@pBFlHc@xHg@z@CpBBbBAvAOhAU`FsAjASpAI|@?dCRdK`B`BRj@D`B@|@A~@IhAQlAYjFkBhHqC~DuA|Aa@fB]|@OpCYvCKtC?bBFlFZ|Ih@T@@?vO~@j@Bj@BvGp@vBPVOLAjDPf@B~@JxAT`Cp@N?h@PnBz@~DpBbBn@lBd@b@D~BH~DItCSn@E~@MpBWv@O|@SjBk@zAo@|@c@l@_@jBkAnC_Cb^u]`VwUbB}Ah]y\\lDmDdB{Az@m@tA{@`By@~@_@pDmAnA_@l@DZJZTTXLXJf@Tx@^dCHTRPGT?VBVHPWf@_@b@mErAU?OEGCKUc@yAA_@Ec@Dk@Jc@DGBO?QEOIIKAIDEB_@I_@SIAQQMSYu@Uq@e@aBIW?MA[AYAQBABABAFI@I?WGSKIGAu@}@c@_@Q]gCkIEg@cDyLOi@COAQByHG{AEwCEqAEcFGiMAeECeLA}J?oB@_DDW@k@Fa@BQHYFOn@gAXm@FKL[Po@Hg@F}@BaABwBFkADq@P}@J[L[LOMQS]KQSs@@sBHgAjAiNZmD?[AgAAeGH{JDgFRsCAyCIaB]yFK}AK{BU{CI_@MkA");
        List<Route> routes = new ArrayList<>();
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);
        routes.add(single);



        adapter.setRoutes(routes);
    }

    static class MapViewHolder extends RecyclerView.ViewHolder {

        ViewGroup root;
        MapView mapView;

        MapViewHolder(View itemView) {
            super(itemView);
            root = (ViewGroup) itemView;
            mapView = (MapView) root.findViewById(R.id.map);
            mapView.onCreate(null);
        }

        void bind(final Route route) {
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.clear();
                    googleMap.addPolyline(new PolylineOptions().addAll(route.points()));

                    final GoogleMap googleMap1 = googleMap;
                    mapView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            if (!mapView.isLaidOut()) {
                                return true;
                            }

                            LatLngBounds.Builder builder = new LatLngBounds.Builder();
                            for (LatLng latLng : route.points()) {
                                builder.include(latLng);
                            }

                            mapView.getViewTreeObserver().removeOnPreDrawListener(this);
                            googleMap1.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 0));
                            return true;
                        }
                    });
                }
            });
        }
    }

    private static class MapsAdapter extends RecyclerView.Adapter<MapViewHolder> {

        private List<Route> routes = new ArrayList<>();

        @Override
        public MapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_route, parent, false);
            return new MapViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MapViewHolder holder, int position) {
            holder.bind(routes.get(position));
        }

        @Override
        public int getItemCount() {
            return routes.size();
        }

        void setRoutes(List<Route> routes) {
            this.routes = routes;
            notifyDataSetChanged();
        }
    }
}
