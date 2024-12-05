package dgu.cse.newsee.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserCategory is a Querydsl query type for UserCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCategory extends EntityPathBase<UserCategory> {

    private static final long serialVersionUID = -1342783536L;

    public static final QUserCategory userCategory = new QUserCategory("userCategory");

    public final StringPath category = createString("category");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserCategory(String variable) {
        super(UserCategory.class, forVariable(variable));
    }

    public QUserCategory(Path<? extends UserCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserCategory(PathMetadata metadata) {
        super(UserCategory.class, metadata);
    }

}

