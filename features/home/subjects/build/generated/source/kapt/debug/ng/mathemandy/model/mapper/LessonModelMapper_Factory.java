package ng.mathemandy.model.mapper;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class LessonModelMapper_Factory implements Factory<LessonModelMapper> {
  @Override
  public LessonModelMapper get() {
    return newInstance();
  }

  public static LessonModelMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LessonModelMapper newInstance() {
    return new LessonModelMapper();
  }

  private static final class InstanceHolder {
    private static final LessonModelMapper_Factory INSTANCE = new LessonModelMapper_Factory();
  }
}
