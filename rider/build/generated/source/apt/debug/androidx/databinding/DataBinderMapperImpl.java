package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.tufike.taxi.rider.DataBinderMapperImpl());
  }
}
