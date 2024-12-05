package dgu.cse.newsee.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNewsMemo is a Querydsl query type for NewsMemo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNewsMemo extends EntityPathBase<NewsMemo> {

    private static final long serialVersionUID = -768398316L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNewsMemo newsMemo = new QNewsMemo("newsMemo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final QNews news;

    public final QUser user;

    public QNewsMemo(String variable) {
        this(NewsMemo.class, forVariable(variable), INITS);
    }

    public QNewsMemo(Path<? extends NewsMemo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNewsMemo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNewsMemo(PathMetadata metadata, PathInits inits) {
        this(NewsMemo.class, metadata, inits);
    }

    public QNewsMemo(Class<? extends NewsMemo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.news = inits.isInitialized("news") ? new QNews(forProperty("news")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

