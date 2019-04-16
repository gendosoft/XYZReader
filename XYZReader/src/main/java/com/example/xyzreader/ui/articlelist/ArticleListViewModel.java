package com.example.xyzreader.ui.articlelist;

import com.example.xyzreader.data.ArticlesRepository;
import com.example.xyzreader.data.model.Article;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 4/16/2019.
 */
public class ArticleListViewModel extends ViewModel {

    private LiveData<List<Article>> articlesListLiveData;

    public ArticleListViewModel(ArticlesRepository articlesRepository) {
        Timber.d("init");
        articlesListLiveData = articlesRepository.getAllArticles();
    }

    public LiveData<List<Article>> getArticlesListLiveData() {
        return articlesListLiveData;
    }
}
