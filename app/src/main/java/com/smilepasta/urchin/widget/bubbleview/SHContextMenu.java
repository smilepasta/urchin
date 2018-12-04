package com.smilepasta.urchin.widget.bubbleview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.smilepasta.urchin.R;
import com.smilepasta.urchin.utils.AppUtil;
import com.smilepasta.urchin.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义上下文菜单
 */
public class SHContextMenu {

    private Context mContext;
    private List<ContextMenuItem> itemList;
    private PopupWindow popupWindow;
    private View contentView;
    private ListView mLvMenuList;
    private MenuAdapter menuAdapter;
    private OnItemSelectListener onItemSelectListener;

    public interface OnItemSelectListener {
        void onItemSelect(int position);
    }

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.onItemSelectListener = onItemSelectListener;
    }

    public SHContextMenu(Context mContext) {
        this.mContext = mContext;
        itemList = new ArrayList<>();
        initPopWindow();
    }

    /**
     * 初始化popwindow菜单
     */
    private void initPopWindow() {
        contentView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_menu, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        mLvMenuList = (ListView) contentView.findViewById(R.id.lv_menu);
        menuAdapter = new MenuAdapter();
        mLvMenuList.setAdapter(menuAdapter);
        mLvMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemSelectListener != null) {
                    if (itemList.get(position).getTag() > 0) {
                        onItemSelectListener.onItemSelect(itemList.get(position).getTag());
                    } else {
                        onItemSelectListener.onItemSelect(position);
                    }
                }
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 设置菜单列表数据源
     *
     * @param itemList
     */
    public void setItemList(List<ContextMenuItem> itemList) {
        this.itemList = itemList;
        menuAdapter.notifyDataSetChanged();
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (popupWindow == null)
            return;
        if (Build.VERSION.SDK_INT >= 24) {
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            //【note!】Gets the screen height without the virtual key
//            WindowManager wm = (WindowManager) popupWindow.getContentView().getContext().getSystemService(Context.WINDOW_SERVICE);
//            int screenHeight = wm.getDefaultDisplay().getHeight();
                /*
                /*
                 * PopupWindow height for match_parent,
                */
//            popupWindow.setHeight(screenHeight - location[1] - anchor.getHeight() - yoff);
            popupWindow.showAtLocation(anchor, Gravity.TOP | Gravity.RIGHT, xoff + UIUtil.dip2px(2), location[1] + anchor.getHeight() + yoff);
        } else {
            popupWindow.showAsDropDown(anchor, xoff, yoff);
        }
    }

    /**
     * 上下文菜单列表适配器
     */
    class MenuAdapter extends BaseAdapter {

        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return itemList == null ? 0 : itemList.size();
        }

        /**
         * Get the data item associated with the specified position in the data set.
         *
         * @param position Position of the item whose data we want within the adapter's
         *                 data set.
         * @return The data at the specified position.
         */
        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        /**
         * Get the row id associated with the specified position in the list.
         *
         * @param position The position of the item within the adapter's data set whose row id we want.
         * @return The id of the item at the specified position.
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * Get a View that displays the data at the specified position in the data set. You can either
         * create a View manually or inflate it from an XML layout file. When the View is inflated, the
         * parent View (GridView, ListView...) will apply default layout parameters unless you use
         * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
         * to specify a root view and to prevent attachment to the root.
         *
         * @param position    The position of the item within the adapter's data set of the item whose view
         *                    we want.
         * @param convertView The old view to reuse, if possible. Note: You should check that this view
         *                    is non-null and of an appropriate type before using. If it is not possible to convert
         *                    this view to display the correct data, this method can create a new view.
         *                    Heterogeneous lists can specify their number of view types, so that this View is
         *                    always of the right type (see {@link #getViewTypeCount()} and
         *                    {@link #getItemViewType(int)}).
         * @param parent      The parent that this view will eventually be attached to
         * @return A View corresponding to the data at the specified position.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.popmenu_item, null);
                viewHolder.mIvIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.mTvTitle.setText(itemList.get(position).getTitle());
            viewHolder.mIvIcon.setImageDrawable(itemList.get(position).getImgDrawable());
            return convertView;
        }

        class ViewHolder {
            TextView mTvTitle;
            ImageView mIvIcon;
        }
    }
}
