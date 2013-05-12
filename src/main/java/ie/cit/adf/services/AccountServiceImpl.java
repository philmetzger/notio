package ie.cit.adf.services;

import ie.cit.adf.domain.dao.AccountRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository repo;

	public AccountServiceImpl(AccountRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void createAccount(String username, String password) {
		repo.addAccount(username, password);
	}

}