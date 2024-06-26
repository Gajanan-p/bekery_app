package com.ssinfomate.bakeryapp.ui.saleretuen;

import com.ssinfomate.bakeryapp.webservices.itemmaster.ItemMasterListModel;

public interface CustomItemClickListener {
   void onItemClick(ItemMasterListModel model, int position);
}
