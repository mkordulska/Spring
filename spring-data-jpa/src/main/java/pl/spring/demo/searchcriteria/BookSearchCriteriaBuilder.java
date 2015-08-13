package pl.spring.demo.searchcriteria;

public class BookSearchCriteriaBuilder {
    
    private BookSearchCriteria underConstruction;
    
    public static BookSearchCriteriaBuilder aBookSearchCriteria() {
        BookSearchCriteriaBuilder builder = new BookSearchCriteriaBuilder();
        builder.underConstruction = new BookSearchCriteria();
        return builder;
    }
    
    public BookSearchCriteriaBuilder withTitle(String title) {
        underConstruction.title = title;
        return this;
    }
    
    public BookSearchCriteriaBuilder withAuthor(String author) {
        underConstruction.author = author;
        return this;
    }
    
    public BookSearchCriteriaBuilder withLibraryName(String libraryName) {
        underConstruction.libraryName = libraryName;
        return this;
    }
    
    public BookSearchCriteria build() {
        BookSearchCriteria builded = underConstruction;
        underConstruction = new BookSearchCriteria();
        return builded;
    }
    
}
