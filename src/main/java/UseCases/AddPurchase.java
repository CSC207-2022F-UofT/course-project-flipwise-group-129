package UseCases;

import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.*;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPurchase implements AddPurchaseBoundaryIn {
    private PurchaseInfo purchaseInfo;
    private Item purchasedItem;
    private List<User> participatingUsers;
    private float price;
    private Group purchaseGroup;
    private UpdatedLists newLists;
    private User buyer;
    private AddPurchaseBoundaryOut presenter;
    private GroupDataInterface groupData;
    private ItemDataInterface itemData;
    private UserDataInterface userData;

    @Override
    public void executeUseCase(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;

        extractInformation(purchaseInfo);

        PlanningList planningList = this.purchaseGroup.getPlanningList();
        planningList.removeFromList(this.purchasedItem);

        PurchaseList purchaseList = this.purchaseGroup.getPurchaseList();
        addToPurchase(purchaseList);

        List<List<String>> planningListItemIds = convertList(planningList);
        List<List<String>> purchasedListItemIds = convertList(purchaseList);
        newLists = new UpdatedLists(planningListItemIds, purchasedListItemIds);

        writeData();

        this.presenter.updateView(newLists);
    }

    private void writeData() {
        try {
            this.groupData.addorUpdateGroup(this.purchaseInfo.getPurchaseGroup(), this.purchaseGroup.toString());
            this.itemData.addorUpdateItem(this.purchaseInfo.getItemId(), this.purchasedItem.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToPurchase(PurchaseList purchaseList) {
        this.purchasedItem.setPrice(this.price);
        this.purchasedItem.setBuyer(this.buyer);
        this.purchasedItem.setUsersInvolved(this.participatingUsers);
        purchaseList.addItems(this.purchasedItem);
    }

    private void extractInformation(PurchaseInfo purchaseInfo) {
        this.groupData = purchaseInfo.getGroupData();
        this.itemData = purchaseInfo.getItemData();
        this.userData = purchaseInfo.getUserData();
        try {
            this.purchasedItem = Item.fromString(this.itemData.itemAsString(this.purchaseInfo.getItemId()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        extractUsers(purchaseInfo);
        this.price = purchaseInfo.getPrice();
        try {
            this.purchaseGroup = Group.fromString(this.groupData.groupAsString(purchaseInfo.getPurchaseGroup()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            this.buyer = User.fromString(this.userData.userAsString(purchaseInfo.getBuyer()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.presenter = purchaseInfo.getPresenter();

    }

    private void extractUsers(PurchaseInfo purchaseInfo) {
        List<String> usernames = purchaseInfo.getUsers();
        for (String username : usernames) {
            try {
                this.participatingUsers.add(User.fromString(this.userData.userAsString(username)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<List<String>> convertList(ItemList inputList) {
        List<Item> tempListItems = inputList.getItems();
        List<List<String>> tempListItemStrings = new ArrayList<List<String>>();
        for (Item item: tempListItems) {
            List<String> tempList = new ArrayList<String>();
            tempList.add(item.getItemId());
            tempList.add(item.getItemName());
            tempListItemStrings.add(tempList);
        }
        return tempListItemStrings;
    }
}
