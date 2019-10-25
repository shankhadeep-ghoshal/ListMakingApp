package org.shankhadeepghoshal.listmakingapp.itemslistflow.viewhandling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.shankhadeepghoshal.listmakingapp.GlobalConstants;
import org.shankhadeepghoshal.listmakingapp.R;
import org.shankhadeepghoshal.listmakingapp.itemslistflow.model.ItemEntity;
import org.shankhadeepghoshal.listmakingapp.utility.NullResolverClass;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListItemsRecyclerViewAdapter extends
        RecyclerView.Adapter<ListItemsRecyclerViewAdapter.ListItemViewHolder> {
    private List<ItemEntity> listItems;

    @Inject
    public ListItemsRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view_holder, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        ItemEntity itemEntity = listItems.get(position);
        holder.populateData(itemEntity);
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void updateItemsList(List<ItemEntity> newList) {
        final ListItemsDiffUtilsCallback listItemsDiffUtilsCallback =
                new ListItemsDiffUtilsCallback(listItems, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(listItemsDiffUtilsCallback);

        this.listItems.clear();
        this.listItems.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public void setListItems(List<ItemEntity> listItems) {
        this.listItems = listItems;
    }

    static class ListItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ItemImage)
        AppCompatImageView itemImage;
        @BindView(R.id.ItemNameTV)
        TextView itemNameTextView;
        @BindView(R.id.ItemUnitsMagnitudeTV)
        TextView itemUnitsMagnitudeTextView;
        @BindView(R.id.UnitTV)
        TextView unitTextView;
        @BindView(R.id.ItemPriceTotalTV)
        TextView itemPriceTotalTV;
        @BindView(R.id.IncreaseQtyBtn)
        AppCompatImageButton increaseQtyButton;
        @BindView(R.id.DecreaseQtyBtn)
        AppCompatImageButton decreaseQtyButton;
        @BindView(R.id.IsItemBought)
        AppCompatCheckBox isItemBought;


        ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void populateData(ItemEntity itemEntity) {

            String unitMagnitude = itemEntity.getUnitSystem() == 1 ?
                    GlobalConstants.UNITS_MAP_METRIC.get(itemEntity.getUnit()) :
                    GlobalConstants.UNITS_MAP_IMPERIAL.get(itemEntity.getUnit());

            // Handling the image views
            Glide.with(this.itemView)
                    .load(itemEntity.getItemImageUrl())
                    .into(itemImage);

            // Handling the text views
            itemNameTextView.setText(NullResolverClass
                    .resolveNullValue(itemEntity.getItemName(), ""));
            itemUnitsMagnitudeTextView.setText(String.valueOf(NullResolverClass
                    .resolveNullValue(itemEntity.getQuantity(),0.0)));
            itemPriceTotalTV.setText(String.valueOf(NullResolverClass
                    .resolveNullValue(itemEntity.getQuantity()*itemEntity.getUnitPrice(),
                            0.0)));
            itemUnitsMagnitudeTextView.setText(unitMagnitude);

            // Handling the buttons
            increaseQtyButton.setOnClickListener(v -> {
                itemEntity.setQuantity(itemEntity.getQuantity() + 1.0);
            });
            decreaseQtyButton.setOnClickListener(v -> {
                if (itemEntity.getQuantity() > 0 && itemEntity.getQuantity() > 1) {
                    itemEntity.setQuantity(itemEntity.getQuantity() - 1.0);
                }
            });

            // Handling other components
            isItemBought.setSelected(itemEntity.isItemBought());
        }
    }

    static class ListItemsDiffUtilsCallback extends DiffUtil.Callback {
        private final List<ItemEntity> oldList;
        private final List<ItemEntity> newList;

        ListItemsDiffUtilsCallback(List<ItemEntity> oldList, List<ItemEntity> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getItemName()
                    .equalsIgnoreCase(newList.get(newItemPosition).getItemName());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}