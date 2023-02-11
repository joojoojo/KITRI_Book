package Book.book_rental.service;

import Book.book_rental.domain.Applications;
import Book.book_rental.repository.ApplicationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationsRepository applicationsRepository;

    @Transactional
    public List<Applications> findAllApplications(){
        return applicationsRepository.findAll();
    }

    @Transactional
    public Applications findOne(Long ApplicationId){
        return applicationsRepository.findOne(ApplicationId);
    }

    public void findApplications(Long ApplicationsId){ // 컨트롤러에서 사용
        Applications applications = applicationsRepository.findOne(ApplicationsId);
        applications.returnExist();
    }
}
