package ro.demo.asignment.ut.service


import ro.demo.asignment.model.GenericEmailRequestModel
import ro.demo.asignment.repository.UserRepository
import ro.demo.asignment.service.BankAccountService
import ro.demo.asignment.service.UserService
import spock.lang.Specification

class UserServiceSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def bankAccountService = Mock(BankAccountService)

    def userService = new UserService(userRepository, bankAccountService)

     def "create user"(){
         given:
         def email =  new GenericEmailRequestModel()
         email.setEmail("asd")

         when:
         userService.registerUser(email)

         then:
         1 * userRepository.save(_) >> _
         1 * bankAccountService.createBankAccount(_) >> _
         0 * _
     }
}
