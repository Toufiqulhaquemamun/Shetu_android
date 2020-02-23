package com.example.amir.shetu.interfaces.ApiDataManager;

import com.example.amir.shetu.model.PdffileList;

import java.util.List;

public interface ProductGradeListener {
    void getFileList(List<PdffileList> files, int endPage, String errorMessage);
}
