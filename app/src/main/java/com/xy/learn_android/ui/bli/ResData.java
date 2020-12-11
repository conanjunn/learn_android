package com.xy.learn_android.ui.bli;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ResData {
    private ResWrap data;

    public ResWrap getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResData{" +
                "data=" + data +
                '}';
    }

    public void setData(ResWrap data) {
        this.data = data;
    }

    public static class ResWrap {
        public ArrayList<ResItem> getCards() {
            return cards;
        }

        public void setCards(ArrayList<ResItem> cards) {
            this.cards = cards;
        }

        private ArrayList<ResItem> cards;
    }

    public static class ResItem {
        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        @NotNull
        @Override
        public String toString() {
            return "ResItem{" +
                    "card='" + card + '\'' +
                    '}';
        }

        private String card = "";
    }

    public static class Card {
        public Pic getItem() {
            return item;
        }

        public void setItem(Pic item) {
            this.item = item;
        }

        private Pic item;


        public static class Pic {
            public ArrayList<Img> getPictures() {
                return pictures;
            }

            public void setPictures(ArrayList<Img> pictures) {
                this.pictures = pictures;
            }

            private ArrayList<Img> pictures;
        }

        public static class Img {
            private Integer img_height;
            private String img_src;

            public Integer getImg_height() {
                return img_height;
            }

            public void setImg_height(Integer img_height) {
                this.img_height = img_height;
            }

            public String getImg_src() {
                return img_src;
            }

            public void setImg_src(String img_src) {
                this.img_src = img_src;
            }

            public Integer getImg_width() {
                return img_width;
            }

            public void setImg_width(Integer img_width) {
                this.img_width = img_width;
            }

            private Integer img_width;
        }
    }
}
