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
        def user = UserGenerator.aUser(id:null,email:"asd")

         when:
         userService.registerUser(email)

         then:
         1 * userRepository.save(user) >> UserGenerator.aUser(id:1,email:"asd")
         1 * bankAccountService.createBankAccount(UserGenerator.aUser(id:1,email:"asd")) >> _
         0 * _
     }
}
