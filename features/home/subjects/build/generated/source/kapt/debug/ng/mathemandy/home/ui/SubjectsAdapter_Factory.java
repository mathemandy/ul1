package ng.mathemandy.home.ui;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import ng.mathemandy.core.imageLoader.ImageLoader;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SubjectsAdapter_Factory implements Factory<SubjectsAdapter> {
  private final Provider<ImageLoader> imageLoaderProvider;

  public SubjectsAdapter_Factory(Provider<ImageLoader> imageLoaderProvider) {
    this.imageLoaderProvider = imageLoaderProvider;
  }

  @Override
  public SubjectsAdapter get() {
    return newInstance(imageLoaderProvider.get());
  }

  public static SubjectsAdapter_Factory create(Provider<ImageLoader> imageLoaderProvider) {
    return new SubjectsAdapter_Factory(imageLoaderProvider);
  }

  public static SubjectsAdapter newInstance(ImageLoader imageLoader) {
    return new SubjectsAdapter(imageLoader);
  }
}
