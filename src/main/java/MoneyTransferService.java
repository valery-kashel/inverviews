import java.math.BigDecimal;

@Service
public class MoneyTransferService {
    @Repository
    private BankAccountsRepository bankAccountsRepository;
    @Repository
    private TransactionsRepository transactionsRepository;

    private final NotificationService notificationService = new NotificationService();

    public void transfer(String fromBankAccount, String toBankAccount, BigDecimal amount) {
        BankAccount from = bankAccountsRepository.findById(fromBankAccount);
        BankAccount to = bankAccountsRepository.findById(toBankAccount);
        Transaction transaction = new Transaction(
            from.id,
            to.id,
            amount
        );
        transactionsRepository.save(transaction);
        notificationService.notify();
    }
}

class BankAccount {
    private String id;
    private String userId;
    private String subscriptionType;
    private BigDecimal amount;
}

class Transaction {
    private String from; // bank account id
    private String to; // bank account id
    private BigDecimal amount;

    public Transaction(String from, String to, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}