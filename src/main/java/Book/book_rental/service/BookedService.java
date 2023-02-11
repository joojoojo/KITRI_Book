package Book.book_rental.service;

import Book.book_rental.domain.Booked;
import Book.book_rental.domain.Rental;
import Book.book_rental.repository.BookedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookedService {

    private final BookedRepository bookedRepository;

    @Transactional
    public List<Booked> findAllbooked(Booked booked){
        return bookedRepository.findAll(booked);
    }
    @Transactional
    public List<Booked> checkBooked(Booked booked){
        return bookedRepository.findAll(booked);
    }
}
