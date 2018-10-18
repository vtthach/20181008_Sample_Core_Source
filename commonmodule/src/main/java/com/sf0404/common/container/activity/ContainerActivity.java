package com.sf0404.common.container.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sf0404.common.R;
import com.sf0404.common.R2;
import com.sf0404.common.container.mode.ToolbarMode;

import butterknife.BindView;

import static com.sf0404.common.utils.ValidationUtils.isNullOrEmpty;

/**
 * Container activity with menu_action_empty frame layout container to host a single fragment
 */
public class ContainerActivity extends BaseActivity {

    private static final String EXTRA_FRAGMENT_CLASS_NAME = "fragment_class_name";
    private static final String EXTRA_FRAGMENT_BUNDLE_ARGS = "fragment_bundle_args";

    private static final String EXTRA_TOOLBAR_MODE = "extra_toolbar_mode";
    private static final String EXTRA_TOOLBAR_TITLE = "extra_toolbar_title";
    private static final String EXTRA_TOOLBAR_TITLE_ID = "extra_toolbar_title_id";
    private static final String EXTRA_TOOLBAR_BKG_ID = "extra_toolbar_bkg_id";
    private static final String EXTRA_TOOLBAR_NAVIGATE_BUTTON_VISIBILITY = "extra_toolbar_navigate_button_visibility";
    private static final String EXTRA_TOOLBAR_COLOR_TINT_ID = "extra_toolbar_color_tint";
    private static final String EXTRA_TOOLBAR_TITLE_GRAVITY = "extra_toolbar_title_gravity";
    public static final int UNUSED_VALUE = -1;

    @BindView(R2.id.toolbar)
    protected Toolbar toolbar;

    @Nullable
    @BindView(R2.id.tvActionTitle)
    protected TextView tvActionTitle;

    @Nullable
    @BindView(R2.id.imgActionLogo)
    protected ImageView imgActionLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        // Inflate fragment only in state create new not in restart(by change orientation, low memory, etc...)
        if (savedInstanceState == null) {
            hostFragment(getDefaultFragmentToHost());
        }
    }

    protected void initActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            setUpActionBar(actionBar);
        }
    }

    protected void setUpActionBar(@NonNull ActionBar actionBar) {
        actionBar.setDisplayShowTitleEnabled(false);
        switchActionMode(getActionTitle());
        updateToolBarMode(actionBar);
    }

    /**
     * Switch between mode title and mode logo
     *
     * @param title
     */
    public void switchActionMode(String title) {
        if (title != null) {
            if (imgActionLogo != null) {
                imgActionLogo.setVisibility(View.GONE);
            }
            if (tvActionTitle != null) {
                tvActionTitle.setVisibility(View.VISIBLE);
                tvActionTitle.setText(title);
            }
        } else {
            if (imgActionLogo != null) {
                imgActionLogo.setVisibility(View.VISIBLE);
            }
            if (tvActionTitle != null) {
                tvActionTitle.setVisibility(View.GONE);
            }
        }
    }

    @Nullable
    public TextView getTvActionTitle() {
        return tvActionTitle;
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (getToolbarMenuId() != 0) {
            getMenuInflater().inflate(getToolbarMenuId(), menu);
        }
        return true;
    }

    protected int getToolbarMenuId() {
        return 0;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setActionTitle(@StringRes int titleid) {
        if (tvActionTitle != null) {
            tvActionTitle.setText(titleid);
        }
    }

    @Override
    protected void onPrepareView() {
        super.onPrepareView();
        initDynamicViewAppBar();
    }

    protected void initDynamicViewAppBar() {
        int layoutIdViewAppBar = getLayoutIdViewAppBar();
        if (layoutIdViewAppBar != UNUSED_VALUE) {
            LayoutInflater.from(this).inflate(layoutIdViewAppBar, findViewById(R.id.toolbar), true);
        }
    }

    protected int getLayoutIdViewAppBar() {
        return R.layout.container_view_appbar;
    }

    private Fragment getDefaultFragmentToHost() {
        Fragment attachedFragment = getAttachHostFragment();
        if (attachedFragment != null) {
            return attachedFragment;
        } else {
            return initFragmentHostFromIntent();
        }
    }

    protected Fragment getAttachHostFragment() {
        // Stub method to override
        return null;
    }

    protected void hostFragment(Fragment fragment) {
        if (fragment != null && getSupportFragmentManager().findFragmentByTag(fragment.getClass().getName()) == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_fragment_container, fragment, fragment.getClass().getName());
            ft.commit();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.container_activity;
    }

    protected String getActionTitle() {
        int rsId = getIntent().getIntExtra(EXTRA_TOOLBAR_TITLE_ID, 0);
        if (rsId != 0) {
            return getString(rsId);
        } else {
            return getIntent().getStringExtra(EXTRA_TOOLBAR_TITLE);
        }
    }

    protected void updateToolBarMode(@NonNull ActionBar actionBar) {
        int toolbarMode = getToolbarMode();
        if (toolbarMode == ToolbarMode.NONE) {
            toolbar.setVisibility(View.GONE);
            actionBar.hide();
        } else {
            int bkgId = getIntent().getIntExtra(EXTRA_TOOLBAR_NAVIGATE_BUTTON_VISIBILITY, View.VISIBLE);
            if (bkgId == View.VISIBLE) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            } else {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
            updateStyle(toolbarMode, actionBar, getIntent());
        }
    }

    private int getColorTint() {
        return getIntent().getIntExtra(EXTRA_TOOLBAR_COLOR_TINT_ID, 0);
    }

    private void updateStyle(int toolbarMode, ActionBar actionBar, Intent intent) {
        int bkgId = intent.getIntExtra(EXTRA_TOOLBAR_BKG_ID, 0);
        // Background
        if (bkgId != 0) {
            actionBar.setBackgroundDrawable(AppCompatResources.getDrawable(this, bkgId));
        }
        // Color tint
        int colorTinId = getColorTint();
        Drawable navigateDrawble = getDrawable(getIndicator(toolbarMode));
        if (colorTinId != 0) {
            if (tvActionTitle != null) {
                tvActionTitle.setTextColor(getResources().getColor(colorTinId));
            }
            if (navigateDrawble != null) {
                Drawable wrappedDrawable = DrawableCompat.wrap(navigateDrawble);
                DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(this, colorTinId));
            }
        }
        // Tittle gravity
        int titleGravity = getTitleGravity();
        if (tvActionTitle != null && titleGravity != 0) {
            Toolbar.LayoutParams layoutParam = (Toolbar.LayoutParams) tvActionTitle.getLayoutParams();
            layoutParam.gravity = titleGravity;
        }
        actionBar.setHomeAsUpIndicator(navigateDrawble);
    }

    private int getTitleGravity() {
        return getIntent().getIntExtra(EXTRA_TOOLBAR_TITLE_GRAVITY, 0);
    }

    private int getIndicator(int toolbarMode) {
        // TODO Use toolbarMode later // NOSONAR
        // TODO expose ic_back resource id
        return R.drawable.ic_back;
    }

    protected int getToolbarMode() {
        return getIntent().getIntExtra(EXTRA_TOOLBAR_MODE, 0);
    }

    private Fragment initFragmentHostFromIntent() {
        String className = getIntent().getStringExtra(EXTRA_FRAGMENT_CLASS_NAME);
        Bundle bundle = getIntent().getBundleExtra(EXTRA_FRAGMENT_BUNDLE_ARGS);
        return instantiateFragment(className, bundle);
    }

    /**
     * Init fragment to host
     *
     * @param fragmentClassName
     * @param data
     */
    public Fragment instantiateFragment(String fragmentClassName, Bundle data) {
        if (fragmentClassName != null) {
            return Fragment.instantiate(this, fragmentClassName, data);
        } else {
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { // NOSONAR
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final class IntentBuilder {
        private Fragment fragment;
        private Activity activity;
        private Bundle data;
        private Class<? extends Fragment> fragmentClass;
        private int requestCode;
        private int flag;
        private boolean isFinishPreviousActivity;
        private int toolbarMode;
        private String toolbarTitle;
        private int toolbarTitleId;
        private int toolbarBkgColorId;
        private int toolbarColorTintId;
        private int navigateButtonVisibility;
        private int titleGravity;

        /**
         * Instantiates a new Intent builder.
         *
         * @param activity the activity
         */
        public IntentBuilder(Activity activity) {
            this.activity = activity;
        }

        /**
         * Instantiates a new Intent builder.
         *
         * @param fragment the activity
         */
        public IntentBuilder(Fragment fragment) {
            this.fragment = fragment;
            this.activity = fragment.getActivity();
        }

        /**
         * Sets fragment class.
         *
         * @param fragmentClass the fragment class
         * @return the fragment class
         */
        public IntentBuilder setFragmentClass(Class<? extends Fragment> fragmentClass) {
            this.fragmentClass = fragmentClass;
            return this;
        }

        /**
         * Sets data.
         *
         * @param data the data
         * @return the data
         */
        public IntentBuilder setData(Bundle data) {
            this.data = data;
            return this;
        }

        /**
         * Sets request code.
         *
         * @param requestCode the request code
         * @return the request code
         */
        public IntentBuilder setRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        /**
         * Sets flag.
         *
         * @param flag the flag
         * @return the flag
         */
        public IntentBuilder setFlag(int flag) {
            this.flag = flag;
            return this;
        }

        /**
         * @param mode seel {@link ToolbarMode}
         * @return
         */
        public IntentBuilder setActionMode(int mode) {
            this.toolbarMode = mode;
            return this;
        }

        public IntentBuilder setActionTitle(String toolbarTitle) {
            this.toolbarTitle = toolbarTitle;
            return this;
        }

        public IntentBuilder setActionTitle(@StringRes int toolbarTitleId) {
            this.toolbarTitleId = toolbarTitleId;
            return this;
        }

        public IntentBuilder actionBackgroundColor(int toolbarBackgroundColor) {
            this.toolbarBkgColorId = toolbarBackgroundColor;
            return this;
        }

        public IntentBuilder colorTintId(int toolbarColorTintId) {
            this.toolbarColorTintId = toolbarColorTintId;
            return this;
        }

        public IntentBuilder navigateButtonVisibility(int visibility) {
            this.navigateButtonVisibility = visibility;
            return this;
        }

        public IntentBuilder titleGravity(int titleGravity) {
            this.titleGravity = titleGravity;
            return this;
        }

        /**
         * Sets finish previous activity.
         *
         * @param finishPrevious the finish previous
         * @return the finish previous activity
         */
        public IntentBuilder setFinishPreviousActivity(boolean finishPrevious) {
            isFinishPreviousActivity = finishPrevious;
            return this;
        }

        public Intent buildIntent() {
            Intent intent = new Intent(activity, ContainerActivity.class);
            if (navigateButtonVisibility != 0) {
                intent.putExtra(EXTRA_TOOLBAR_NAVIGATE_BUTTON_VISIBILITY, navigateButtonVisibility);
            }
            if (titleGravity != 0) {
                intent.putExtra(EXTRA_TOOLBAR_TITLE_GRAVITY, titleGravity);
            }
            if (toolbarBkgColorId != 0) {
                intent.putExtra(EXTRA_TOOLBAR_BKG_ID, toolbarBkgColorId);
            }
            if (toolbarColorTintId != 0) {
                intent.putExtra(EXTRA_TOOLBAR_COLOR_TINT_ID, toolbarColorTintId);
            }
            if (toolbarTitleId != 0) {
                intent.putExtra(EXTRA_TOOLBAR_TITLE_ID, toolbarTitleId);
            }
            if (toolbarMode != 0) {
                intent.putExtra(EXTRA_TOOLBAR_MODE, toolbarMode);
            }
            if (!isNullOrEmpty(toolbarTitle)) {
                intent.putExtra(EXTRA_TOOLBAR_TITLE, toolbarTitle);
            }
            if (flag != UNUSED_VALUE) {
                intent.addFlags(flag);
            }
            intent.putExtra(EXTRA_FRAGMENT_CLASS_NAME, fragmentClass.getName());
            if (data != null) {
                intent.putExtra(EXTRA_FRAGMENT_BUNDLE_ARGS, data);
            }
            return intent;
        }

        /**
         * Start.
         */
        public void start() {
            Intent intent = buildIntent();
            if (requestCode != UNUSED_VALUE) {
                if (fragment != null) {
                    fragment.startActivityForResult(intent, requestCode);
                } else {
                    activity.startActivityForResult(intent, requestCode);
                }
            } else {
                activity.startActivity(intent);
            }
            if (isFinishPreviousActivity) {
                activity.finish();
            }
        }
    }
}
