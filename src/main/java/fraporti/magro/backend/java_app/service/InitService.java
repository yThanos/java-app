package fraporti.magro.backend.java_app.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import fraporti.magro.backend.java_app.model.Book;
import fraporti.magro.backend.java_app.model.Interaction;
import fraporti.magro.backend.java_app.model.Permission;
import fraporti.magro.backend.java_app.model.Status;
import fraporti.magro.backend.java_app.model.User;

import java.util.ArrayList;
import java.util.List;
import fraporti.magro.backend.java_app.repository.BookRepository;
import fraporti.magro.backend.java_app.repository.InteractionRepository;
import fraporti.magro.backend.java_app.repository.PermissionRepository;
import fraporti.magro.backend.java_app.repository.StatusRepository;
import fraporti.magro.backend.java_app.repository.UserRepository;

@Service
public class InitService {
    private List<Book> books = new ArrayList<Book>(List.of(
        Book.builder().title("The Lord of the Rings").author("J.R.R. Tolkien").avgRating(4.5)
        .imgUrl("https://i.redd.it/pu1i0ekgyhu81.jpg").build(),
        Book.builder().title("Harry Potter and the Philosopher's Stone").author("J.K. Rowling").avgRating(4.8)
        .imgUrl("https://cdn.kobo.com/book-images/6750d058-29cb-4626-9c12-a62e816a80cc/166/300/False/harry-potter-and-the-philosopher-s-stone-3.jpg").build(),
        Book.builder().title("The Hobbit").author("J.R.R. Tolkien").avgRating(4.3)
        .imgUrl("https://m.media-amazon.com/images/I/712cDO7d73L._AC_UF1000,1000_QL80_.jpg").build(),
        Book.builder().title("The Catcher in the Rye").author("J. D. Salinger").avgRating(4.1)
        .imgUrl("https://m.media-amazon.com/images/I/8125BDk3l9L._AC_UF1000,1000_QL80_.jpg").build(),
        Book.builder().title("The Great Gatsby").author("F. Scott Fitzgerald").avgRating(4.2)
        .imgUrl("https://i0.wp.com/americanwritersmuseum.org/wp-content/uploads/2018/02/CK-3.jpg").build(),
        Book.builder().title("To Kill a Mockingbird").author("Harper Lee").avgRating(4.4)
        .imgUrl("https://m.media-amazon.com/images/I/81aY1lxk+9L._AC_UF1000,1000_QL80_.jpg").build(),
        Book.builder().title("1984").author("George Orwell").avgRating(4.6)
        .imgUrl("https://m.media-amazon.com/images/I/61ZewDE3beL._AC_UF1000,1000_QL80_.jpg").build(),
        Book.builder().title("Animal Farm").author("George Orwell").avgRating(4.7)
        .imgUrl("https://m.media-amazon.com/images/I/71JUJ6pGoIL._AC_UF1000,1000_QL80_.jpg").build(),
        Book.builder().title("The Da Vinci Code").author("Dan Brown").avgRating(4.0)
        .imgUrl("https://m.media-amazon.com/images/I/815WORuYMML._AC_UF1000,1000_QL80_.jpg").build(),
        Book.builder().title("Berserk").author("Kentaro Miura").avgRating(4.9).genre("Fantasia sombria, Alta fantasia, Espada e feitiçaria")
            .year(1989).publisher("Panini comics").volumes(41).imgUrl("https://i.pinimg.com/originals/0c/fa/2c/0cfa2c5b44ae7bcd39531f80989fa69a.jpg").build()
    ));

    private List<User> users = new ArrayList<User>(List.of(
        User.builder().username("vitor@gmail.com")
            .password(new BCryptPasswordEncoder().encode("1234"))
            .name("Vitor Fraporti").permissions(List.of(new Permission(1L, "USER", "User"))).build(),
        User.builder().username("bianca@gmail.com")
            .password(new BCryptPasswordEncoder().encode("1234"))
            .name("Bianca Magro").permissions(List.of(new Permission(1L, "USER", "User"))).build()
    ));

    public InitService(BookRepository bookRepository, UserRepository userRepository, InteractionRepository interactionRepository, PermissionRepository permissionRepository, StatusRepository statusRepository) {
        permissionRepository.save(new Permission(1L, "USER", "User"));
        statusRepository.saveAll(List.of(
            new Status(1L, "Lido"),
            new Status(2L, "Lendo"),
            new Status(3L, "Quero ler")
        ));
        books = bookRepository.saveAll(books);
        users = userRepository.saveAll(users);
        List<Interaction> interactions = new ArrayList<Interaction>(List.of(
            Interaction.builder().user(users.get(0)).book(books.get(0)).rating(5)
                .comment("Senhor dos aneis MUITO goated, que obra atemporal!").build(),
            Interaction.builder().user(users.get(0)).book(books.get(1)).rating(4)
                .comment("Muito melhor que os filmes kkkk").build(),
            Interaction.builder().user(users.get(0)).book(books.get(2)).rating(4)
                .comment("Muito bom, não é Senhor dos aneis mas quase bom quanto!").build(),
            Interaction.builder().user(users.get(0)).book(books.get(9)).rating(5).bookmarked(true)
            .comment("A MELHOR OBRA DE FICÇÃO JÁ FEITA NA HISTORIA, descansa em paz Miura").build(),
            Interaction.builder().user(users.get(0)).book(books.get(3)).rating(2).build(),
            Interaction.builder().user(users.get(0)).book(books.get(4)).rating(1).build(),
            Interaction.builder().user(users.get(0)).book(books.get(5)).rating(2).build(),
            Interaction.builder().user(users.get(0)).book(books.get(6)).rating(3).build(),
            Interaction.builder().user(users.get(0)).book(books.get(7)).rating(4).build(),
            Interaction.builder().user(users.get(0)).book(books.get(8)).rating(5).build(),
            Interaction.builder().user(users.get(1)).book(books.get(0)).rating(1).build(),
            Interaction.builder().user(users.get(1)).book(books.get(1)).rating(2).build(),
            Interaction.builder().user(users.get(1)).book(books.get(2)).rating(3).build(),
            Interaction.builder().user(users.get(1)).book(books.get(3)).rating(4).build(),
            Interaction.builder().user(users.get(1)).book(books.get(4)).rating(5).build(),
            Interaction.builder().user(users.get(1)).book(books.get(5)).rating(4).build(),
            Interaction.builder().user(users.get(1)).book(books.get(6)).rating(3).build(),
            Interaction.builder().user(users.get(1)).book(books.get(7)).rating(2).build(),
            Interaction.builder().user(users.get(1)).book(books.get(8)).rating(1).build()
        ));
        interactionRepository.saveAll(interactions);
    }
}
