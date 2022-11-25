package kz.lab5_kre17;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; // Карта
    private TextView textView; // Текстовый компонет

    private String markerTitle = ""; // Название выбранного маркера
    private String markerFileName = ""; // Имя файла с подробными данными выбранного маркера
    private Spinner spinnerReligioznieObedineniya;

    private final int SITYSCALE = 15; // Масштаб для отображения карты

    static final LatLng startMarker = new LatLng(52.2873032, 76.9674023); // Начальный маркер
    static final LatLng marker1 = new LatLng(52.29523944617888, 76.97775141769712); // Павлодарское общество сознания Кришны
    static final LatLng marker2 = new LatLng(52.27940665782127, 76.94140059157765); // Еврейская община Павлодарской области
    static final LatLng marker3 = new LatLng(52.28279323585206, 76.95884572091495); // Христианская община Свидетелей Иеговы Павлодарской области
    static final LatLng marker4 = new LatLng(52.2813220160322, 76.94701149832497); // Христианская Новоапостольская церковь г.Павлодар
    static final LatLng marker5 = new LatLng(52.24936421987458, 76.9831101510945); // Поместная церковь христиан Адвентистов седьмого дня г.Павлодар
    static final LatLng marker6 = new LatLng(52.27373717848398, 76.95705713969102); // Христианская Евангельская пятидесятническая Церковь «Источник жизни»
    static final LatLng marker7 = new LatLng(52.27292246674076, 76.95668470920292); // Христианская пресвитерианская церковь «Алия»
    static final LatLng marker8 = new LatLng(52.2905271308268, 76.99270928356094); // Христиан Веры евангельской «Церковь Иисуса Христа»
    static final LatLng marker9 = new LatLng(52.29230191580545, 76.98948156246298); // Миссия благотворения и евангелизации «ИОСИФ» Христиан Веры Евангельской (пятидесятников) г.Павлодар
    static final LatLng marker10 = new LatLng(52.292504026203325, 76.98976824381825); // Церковь Христиан веры Евангельской «Новая жизнь»
    static final LatLng marker11 = new LatLng(52.273292044570255, 76.93689568615963); // Евангельская Методистская Церковь «Любовь и Святость»
    static final LatLng marker12 = new LatLng(52.297724157156026, 76.94489152716055); // Методистская церковь «Надежда»
    static final LatLng marker13 = new LatLng(52.278292487208915, 76.94551804065442); // Церковь «Надежда на Христа Евангельских христиан-баптистов г.Павлодар
    static final LatLng marker14 = new LatLng(52.28051826988512, 76.95283884828196); // Евангельские христиане–баптисты «Шындық»
    static final LatLng marker15 = new LatLng(52.25559510788654, 76.95853195092003); // Павлодарская община Евангельских христиан-баптистов
    static final LatLng marker16 = new LatLng(52.28919607074969, 76.98701591349155); // Евангелическо-лютеранский приход Павлодарской области
    static final LatLng marker17 = new LatLng(52.297505951769246, 76.98719304872655); // Украинская Греко-католическая церковь» «Святых Петра и Павла»
    static final LatLng marker18 = new LatLng(52.289789117288, 77.00742679832511); // Римско-Католический Приход «Святой Терезы Младенца Иисуса»
    static final LatLng marker19 = new LatLng(52.276037941010095, 76.98434727041031); // Приход Михаило–Архангельского храма г.Павлодар Павлодарской и Экибастузской епархии
    static final LatLng marker20 = new LatLng(52.39696488184515, 76.85100556337365); // Петропавловский женский монастырь Павлодарской и Экибастузской епархии
    static final LatLng marker21 = new LatLng(52.263007879640625, 76.7890877262188); // Никольский храм г.Павлодар Павлодарской и Экибастузской епархии
    static final LatLng marker22 = new LatLng(52.27356261708763, 76.93800428447425); // Приход Христово-рождественского собора г.Павлодар Павлодарской и Экибастузской епархии
    static final LatLng marker23 = new LatLng(52.29608828736668, 76.92844997100167); // Павлодарской и Экибастузской епархии Православной церкви Казахстана
    static final LatLng marker24 = new LatLng(52.29695967269359, 76.92957673206024); // Приход Благовещенского кафедрального собора г. Павлодар Павлодарской и Экибастузской епархии
    static final LatLng marker25 = new LatLng(52.303080067260254, 77.00629694514721); // Умар әл-Фаруқ мешіті
    static final LatLng marker26 = new LatLng(52.24497308286917, 76.7752418574977); // Дайырбек-ата
    static final LatLng marker27 = new LatLng(52.184414847655006, 77.03053078842994); // Шайқы–ата мешіті
    static final LatLng marker28 = new LatLng(52.24451652158308, 76.99811600900883); // Ар Раһман мешіті
    static final LatLng marker29 = new LatLng(52.27306685243435, 76.95274519741052); // Бижан мешіті
    static final LatLng marker30 = new LatLng(52.25618152964586, 76.95272265655501); // Ғазиз мешіті
    static final LatLng marker31 = new LatLng(52.34047031993823, 76.8943174830027); // Қасым қажы мешіті
    static final LatLng marker32 = new LatLng(52.26060641794339, 76.78856833533334); // Ысқақ хазрет мешіті
    static final LatLng marker33 = new LatLng(52.292523897858146, 76.99821544603594); // Әбу Бакір Сыддық мешіті
    static final LatLng marker34 = new LatLng(52.277635851915534, 76.94322632179622); // Ақмешіт
    static final LatLng marker35 = new LatLng(52.286709238942194, 76.96215545655528); // Машһүр Жүсіп мешіті
/* ИСЛАМ        = 11 / 35 = 31.428571428571427 %
   ХРИСТИАНСТВО = 22 / 35 = 62.857142857142854 %
   ИУДАИЗМ      =  1 / 35 =  2.857142857142857 %
   КРИШНОИТЫ    =  1 / 35 =  2.857142857142857 %
*/

    private static final float ALPHA = 0.2f; // Коэффициент прозрачности для маркеров

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Доступ к карте
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView = (TextView) findViewById(R.id.textViewInfo); // Доступ к компоненту "textViewInfo"
        spinnerReligioznieObedineniya = (Spinner) findViewById(R.id.spinnerReligioznieObedineniya);
        spinnerReligioznieObedineniya.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String idMarker = "m".concat(String.valueOf(spinnerReligioznieObedineniya.getSelectedItemId() + 1));
                onMarkerClick(idMarker);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Обычный тип карты
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Добавление маркера на карту с текстом
        mMap.addMarker(new MarkerOptions().position(startMarker).title((getString(R.string.startMarker_title))));

        // Добавление маркера на карту с текстом, иконкой и полупрозрачностью
        mMap.addMarker(new MarkerOptions().position(marker1).title((getString(R.string.marker1_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.krishnaiti)).alpha(ALPHA).
                snippet(getString(R.string.marker1_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker2).title((getString(R.string.marker2_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.evrei)).alpha(ALPHA).
                snippet(getString(R.string.marker2_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker3).title((getString(R.string.marker3_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker3_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker4).title((getString(R.string.marker4_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker4_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker5).title((getString(R.string.marker5_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker5_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker6).title((getString(R.string.marker6_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker6_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker7).title((getString(R.string.marker7_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker7_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker8).title((getString(R.string.marker8_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker8_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker9).title((getString(R.string.marker9_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker9_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker10).title((getString(R.string.marker10_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker10_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker11).title((getString(R.string.marker11_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker11_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker12).title((getString(R.string.marker12_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker12_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker13).title((getString(R.string.marker13_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker13_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker14).title((getString(R.string.marker14_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker14_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker15).title((getString(R.string.marker15_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker15_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker16).title((getString(R.string.marker16_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker16_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker17).title((getString(R.string.marker17_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker17_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker18).title((getString(R.string.marker18_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker18_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker19).title((getString(R.string.marker19_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker19_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker20).title((getString(R.string.marker20_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker20_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker21).title((getString(R.string.marker21_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker21_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker22).title((getString(R.string.marker22_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker22_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker23).title((getString(R.string.marker23_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker23_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker24).title((getString(R.string.marker24_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.tzerkov)).alpha(ALPHA).
                snippet(getString(R.string.marker24_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker25).title((getString(R.string.marker25_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker25_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker26).title((getString(R.string.marker26_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker26_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker27).title((getString(R.string.marker27_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker27_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker28).title((getString(R.string.marker28_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker28_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker29).title((getString(R.string.marker29_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker29_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker30).title((getString(R.string.marker30_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker30_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker31).title((getString(R.string.marker31_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker31_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker32).title((getString(R.string.marker32_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker32_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker33).title((getString(R.string.marker33_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker33_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker34).title((getString(R.string.marker34_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker34_txt_click)));

        mMap.addMarker(new MarkerOptions().position(marker35).title((getString(R.string.marker35_title))).icon(
                        bitmapDescriptorFromVector(this, R.drawable.mechet)).alpha(ALPHA).
                snippet(getString(R.string.marker35_txt_click)));


        //Разрешение изменения масштаба карты
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Проверка на включенный GPS и позиционирование на карте
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Показать текущее местоположение по GPS
            mMap.setMyLocationEnabled(true);
        }

        // Переход просмотра на карте на нужный маркер c зумом
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startMarker, SITYSCALE));

        // Инициализация стартового маркера
        onMarkerClick(getString(R.string.startMarker_id));

        // Обработчик нажатия на маркеры карты
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                MapsActivity.this.onMarkerClick(marker.getId());
                return true;
            }
        });
    }

    // Нажатие на маркер
    public void onMarkerClick(String idMarker) {
        switch (idMarker) {
            case "m0":
                doClickMarker(startMarker, getString(R.string.startMarker_info),
                        getString(R.string.startMarker_title), getString(R.string.startMarker_file));
                break;
            case "m1":
                doClickMarker(marker1, getString(R.string.marker1_info),
                        getString(R.string.marker1_title), getString(R.string.marker1_file));
                break;
            case "m2":
                doClickMarker(marker2, getString(R.string.marker2_info),
                        getString(R.string.marker2_title), getString(R.string.marker2_file));
                break;
            case "m3":
                doClickMarker(marker3, getString(R.string.marker3_info),
                        getString(R.string.marker3_title), getString(R.string.marker3_file));
                break;
            case "m4":
                doClickMarker(marker4, getString(R.string.marker4_info),
                        getString(R.string.marker4_title), getString(R.string.marker4_file));
                break;
            case "m5":
                doClickMarker(marker5, getString(R.string.marker5_info),
                        getString(R.string.marker5_title), getString(R.string.marker5_file));
                break;
            case "m6":
                doClickMarker(marker6, getString(R.string.marker6_info),
                        getString(R.string.marker6_title), getString(R.string.marker6_file));
                break;
            case "m7":
                doClickMarker(marker7, getString(R.string.marker7_info),
                        getString(R.string.marker7_title), getString(R.string.marker7_file));
                break;
            case "m8":
                doClickMarker(marker8, getString(R.string.marker8_info),
                        getString(R.string.marker8_title), getString(R.string.marker8_file));
                break;
            case "m9":
                doClickMarker(marker9, getString(R.string.marker9_info),
                        getString(R.string.marker9_title), getString(R.string.marker9_file));
                break;
            case "m10":
                doClickMarker(marker10, getString(R.string.marker10_info),
                        getString(R.string.marker10_title), getString(R.string.marker10_file));
                break;
            case "m11":
                doClickMarker(marker11, getString(R.string.marker11_info),
                        getString(R.string.marker11_title), getString(R.string.marker11_file));
                break;
            case "m12":
                doClickMarker(marker12, getString(R.string.marker12_info),
                        getString(R.string.marker12_title), getString(R.string.marker12_file));
                break;
            case "m13":
                doClickMarker(marker13, getString(R.string.marker13_info),
                        getString(R.string.marker13_title), getString(R.string.marker13_file));
                break;
            case "m14":
                doClickMarker(marker14, getString(R.string.marker14_info),
                        getString(R.string.marker14_title), getString(R.string.marker14_file));
                break;
            case "m15":
                doClickMarker(marker15, getString(R.string.marker15_info),
                        getString(R.string.marker15_title), getString(R.string.marker15_file));
                break;
            case "m16":
                doClickMarker(marker16, getString(R.string.marker16_info),
                        getString(R.string.marker16_title), getString(R.string.marker16_file));
                break;
            case "m17":
                doClickMarker(marker17, getString(R.string.marker17_info),
                        getString(R.string.marker17_title), getString(R.string.marker17_file));
                break;
            case "m18":
                doClickMarker(marker18, getString(R.string.marker18_info),
                        getString(R.string.marker18_title), getString(R.string.marker18_file));
                break;
            case "m19":
                doClickMarker(marker19, getString(R.string.marker19_info),
                        getString(R.string.marker19_title), getString(R.string.marker19_file));
                break;
            case "m20":
                doClickMarker(marker20, getString(R.string.marker20_info),
                        getString(R.string.marker20_title), getString(R.string.marker20_file));
                break;
            case "m21":
                doClickMarker(marker21, getString(R.string.marker21_info),
                        getString(R.string.marker21_title), getString(R.string.marker21_file));
                break;
            case "m22":
                doClickMarker(marker22, getString(R.string.marker22_info),
                        getString(R.string.marker22_title), getString(R.string.marker22_file));
                break;
            case "m23":
                doClickMarker(marker23, getString(R.string.marker23_info),
                        getString(R.string.marker23_title), getString(R.string.marker23_file));
                break;
            case "m24":
                doClickMarker(marker24, getString(R.string.marker24_info),
                        getString(R.string.marker24_title), getString(R.string.marker24_file));
                break;
            case "m25":
                doClickMarker(marker25, getString(R.string.marker25_info),
                        getString(R.string.marker25_title), getString(R.string.marker25_file));
                break;
            case "m26":
                doClickMarker(marker26, getString(R.string.marker26_info),
                        getString(R.string.marker26_title), getString(R.string.marker26_file));
                break;
            case "m27":
                doClickMarker(marker27, getString(R.string.marker27_info),
                        getString(R.string.marker27_title), getString(R.string.marker27_file));
                break;
            case "m28":
                doClickMarker(marker28, getString(R.string.marker28_info),
                        getString(R.string.marker28_title), getString(R.string.marker28_file));
                break;
            case "m29":
                doClickMarker(marker29, getString(R.string.marker29_info),
                        getString(R.string.marker29_title), getString(R.string.marker29_file));
                break;
            case "m30":
                doClickMarker(marker30, getString(R.string.marker30_info),
                        getString(R.string.marker30_title), getString(R.string.marker30_file));
                break;
            case "m31":
                doClickMarker(marker31, getString(R.string.marker31_info),
                        getString(R.string.marker31_title), getString(R.string.marker31_file));
                break;
            case "m32":
                doClickMarker(marker32, getString(R.string.marker32_info),
                        getString(R.string.marker32_title), getString(R.string.marker32_file));
                break;
            case "m33":
                doClickMarker(marker33, getString(R.string.marker33_info),
                        getString(R.string.marker33_title), getString(R.string.marker33_file));
                break;
            case "m34":
                doClickMarker(marker34, getString(R.string.marker34_info),
                        getString(R.string.marker34_title), getString(R.string.marker34_file));
                break;
            case "m35":
                doClickMarker(marker35, getString(R.string.marker35_info),
                        getString(R.string.marker35_title), getString(R.string.marker35_file));
                break;
        }
    }

    // Обработка нажатия на маркер
    public void doClickMarker(LatLng marker, String info, String markerTitle, String markerFileName) {
        this.markerTitle = markerTitle;
        this.markerFileName = markerFileName;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, SITYSCALE));
        findViewById(R.id.scrollViewTextViewInfo).scrollTo(0, 0);
        if (Build.VERSION.SDK_INT >= 24) {
            textView.setText(Html.fromHtml(info, Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(info));
        }
    }

    // Нажатие на кнопку маркера
    public void onClickButtonMarker(View view) {
        String idMarker = view.getTag().toString();
        onMarkerClick(idMarker);
    }

    // Обработчик "Подробно"
    public void detailExpandOnClick(View view) {
        if (!markerFileName.equals("")) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(getString(R.string.tMarker), markerTitle);
            intent.putExtra(getString(R.string.mfile), markerFileName);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.selectOb, Toast.LENGTH_SHORT).show();
        }
    }

}
