package dgu.cse.newsee.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarm is a Querydsl query type for Alarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarm extends EntityPathBase<Alarm> {

    private static final long serialVersionUID = -931360182L;

    public static final QAlarm alarm = new QAlarm("alarm");

    public final BooleanPath active = createBoolean("active");

    public final ListPath<String, StringPath> days = this.<String, StringPath>createList("days", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath period = createString("period");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QAlarm(String variable) {
        super(Alarm.class, forVariable(variable));
    }

    public QAlarm(Path<? extends Alarm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlarm(PathMetadata metadata) {
        super(Alarm.class, metadata);
    }

}

