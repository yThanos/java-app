package fraporti.magro.backend.java_app.model.projections;

public interface BookProjection {
  Long getId();
  String getTitle();
  String getAuthor();
  Integer getYear();
  String getGenre();
  String getPublisher();
  Integer getVolumes();
  Double getAvgRating();
}
