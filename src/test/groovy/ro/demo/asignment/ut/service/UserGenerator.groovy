package ro.demo.asignment.ut.service

import ro.demo.asignment.entity.User

class UserGenerator {
    static aUser(overrides = [:]) {
        Map values = [
                id   : 1,
                email: "username"
        ]

        values << overrides
        return User.newInstance(values)
    }
}
