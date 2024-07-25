package fraporti.magro.backend.java_app.model.projections;

public interface InteractionProjection {
  Long getId();
  Integer getRating();
  String getComment();
  Boolean getBookmarked();
  BookProjection getBook();
  UserProjection getUser();
  StatusProjection getStatus();
}
