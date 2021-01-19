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
public final class RecentVideosAdapter_Factory implements Factory<RecentVideosAdapter> {
  private final Provider<ImageLoader> imageLoaderProvider;

  public RecentVideosAdapter_Factory(Provider<ImageLoader> imageLoaderProvider) {
    this.imageLoaderProvider = imageLoaderProvider;
  }

  @Override
  public RecentVideosAdapter get() {
    return newInstance(imageLoaderProvider.get());
  }

  public static RecentVideosAdapter_Factory create(Provider<ImageLoader> imageLoaderProvider) {
    return new RecentVideosAdapter_Factory(imageLoaderProvider);
  }

  public static RecentVideosAdapter newInstance(ImageLoader imageLoader) {
    return new RecentVideosAdapter(imageLoader);
  }
}
