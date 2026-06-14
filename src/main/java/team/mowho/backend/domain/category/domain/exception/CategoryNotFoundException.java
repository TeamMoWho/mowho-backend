package team.mowho.backend.domain.category.domain.exception;

import team.mowho.backend.global.exception.CustomException;

public class CategoryNotFoundException extends CustomException {

    public CategoryNotFoundException() {
        super(CategoryExceptionCode.CATEGORY_NOT_FOUND);
    }

}
