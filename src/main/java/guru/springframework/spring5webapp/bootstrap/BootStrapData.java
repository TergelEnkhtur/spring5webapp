package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher bantham = new Publisher();
        bantham.setName("Bantam Spectra");
        bantham.setCity("New York City");
        bantham.setState("NY");

        Publisher bloomsbury = new Publisher();
        bloomsbury.setName("Bloomsbury");
        bloomsbury.setCity("London");
        bloomsbury.setState("N/A");

        publisherRepository.save(bantham);
        publisherRepository.save(bloomsbury);
        System.out.println("Publisher Count: " + publisherRepository.count());

        Author george = new Author("George", "Martin");
        Book GoT = new Book("A Game of Thrones", "0-553-10354-7");
        george.getBooks().add(GoT);
        GoT.getAuthors().add(george);

        GoT.setPublisher(bantham);
        bantham.getBooks().add(GoT);

        authorRepository.save(george);
        bookRepository.save(GoT);
        publisherRepository.save(bantham);

        Author JKR = new Author("Joanne", "Rowling");
        Book HPPS = new Book("Harry Potter and the Philosopher's Stone", "978-0-7475-3269-9");
        JKR.getBooks().add(HPPS);
        HPPS.getAuthors().add(JKR);

        HPPS.setPublisher(bloomsbury);
        bloomsbury.getBooks().add(HPPS);

        authorRepository.save(JKR);
        bookRepository.save(HPPS);
        publisherRepository.save(bloomsbury);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Bloomsbury Number of Books: " + bloomsbury.getBooks().size());
        System.out.println("Bantham Number of Books: " + bantham.getBooks().size());
    }
}
