package com.example.mybreak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.cardview.widget.CardView;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marcoscg.dialogsheet.DialogSheet;
import com.skydoves.powermenu.CustomPowerMenu;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.PowerMenu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity{

    private RelativeLayout content, rel2;
    private CardView placeImage;
    private TextView placeName, placeDuration, placePrice;
    private TextView showAll;
    private FloatingActionButton bookFlightButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookFlightButton = findViewById(R.id.bookFlightButton);
        bookFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });

        content = findViewById(R.id.contentRel);
        placeImage = findViewById(R.id.placeImageId);
        placeName = findViewById(R.id.placeName);
        placeDuration = findViewById(R.id.placeDuration);
        placePrice = findViewById(R.id.placePrice);
        showAll = findViewById(R.id.showAllId);
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //attaching onclick transition animation
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, shared_full.class);

                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View, String>(placeImage, "imageTransition");
                pairs[1] = new Pair<View, String>(placeName, "nameTransition");
                pairs[2] = new Pair<View, String>(placeDuration, "durationTransition");
                pairs[3] = new Pair<View, String>(placePrice, "priceTransition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent, options.toBundle());

            }
        });
}
   public void showDialog(){

       RoundedBottomSheetDialog mBottomSheetDialog = new RoundedBottomSheetDialog(MainActivity.this);
       mBottomSheetDialog.setContentView(R.layout.popup_book_flight);
       mBottomSheetDialog.show();
   }

   public void showStyledPopupMenu(View view) {
        showPopupMenu(view, true, R.style.MyPopupOtherStyle);
    }

    private void showPopupMenu(View anchor, boolean isWithIcons, int style) {

        Context wrapper = new ContextThemeWrapper(this, style);


        PopupMenu popup = new PopupMenu(wrapper, anchor);


        if (isWithIcons) {
            try {
                Field[] fields = popup.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if ("mPopup".equals(field.getName())) {
                        field.setAccessible(true);
                        Object menuPopupHelper = field.get(popup);
                        Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                        Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                        setForceIcons.invoke(menuPopupHelper, true);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        popup.getMenuInflater().inflate(R.menu.menu_filter, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.India:
                        Toast.makeText(MainActivity.this, "India", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.foreign:
                        Toast.makeText(MainActivity.this, "Foreign", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.adventure:
                        Toast.makeText(MainActivity.this, "Adventure", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sports:
                        Toast.makeText(MainActivity.this, "Sports", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popup.show();

    }

}


