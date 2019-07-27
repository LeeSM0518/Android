package com.example.multimemo;

public class MemoListItem {

    // 데이터 배열
    private String[] mData;
    // 아이템 ID
    private String mId;

    // 아이템 선택 여부
    private boolean mSelectable = true;

    // 아이콘과 데이터 배열 초기화
    public MemoListItem(String itemId, String[] obj) {
        mId = itemId;
        mData = obj;
    }

    // 아이템 ID와 데이터 배열 초기화
    public MemoListItem(String memoId, String memoTitle,
                        String memoDate, String memoText,
                        String idHandWriting, String uriHandwriting,
                        String idPhoto, String uriPhoto,
                        String idVideo, String uriVideo,
                        String idVoice, String uriVoice) {
        mId = memoId;
        mData = new String[11];
        mData[0] = memoTitle;
        mData[1] = memoDate;
        mData[2] = memoText;
        mData[3] = idHandWriting;
        mData[4] = uriHandwriting;
        mData[5] = idPhoto;
        mData[6] = uriPhoto;
        mData[7] = idVideo;
        mData[8] = uriVideo;
        mData[9] = idVoice;
        mData[10] = uriVoice;
    }

    public boolean isSelectable() {
        return mSelectable;
    }

    public void setSelectable(boolean selectable) {
        mSelectable = selectable;
    }

    public String getId() {
        return mId;
    }

    public void setId(String itemId) {
        mId = itemId;
    }

    public String[] getData() {
        return mData;
    }

    public String getData(int index) {
        if (mData == null || index >= mData.length) {
            return null;
        }
        return mData[index];
    }

    public void setData(String[] obj) {
        mData = obj;
    }

    // 입력된 객체와 비교
    public int compareTo(MemoListItem other) {
        if (mData != null) {
            Object[] otherData = other.getData();
            if (mData.length == otherData.length) {
                for (int i = 0; i < mData.length; i++) {
                    if (!mData[i].equals(otherData)) {
                        return -1;
                    } else {
                        return -1;
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return 0;
    }

}
