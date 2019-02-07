(ns nomis-random-playing-clojure-repo.core-test
  (:require [midje.sweet :refer :all]
            [nomis-random-playing-clojure-repo.core :as sut]))

;;;; ___________________________________________________________________________
;;;; ---- sut/do1 ----

(fact "`sut/do1` works"

      (fact "Fails to compile when there are no forms"
            (macroexpand-1 '(sut/do1))
            => (throws clojure.lang.ArityException))

      (fact "Returns value of first form when there is one form"
            (sut/do1 :a)
            => :a)

      (fact "Returns value of first form when there are two forms"
            (sut/do1
             :a
             :b)
            => :a)

      (fact "Returns value of first form when there are three forms"
            (sut/do1
             :a
             :b
             :c)
            => :a)

      (fact "Forms are evaluated in correct order"
            (let [side-effect-place (atom [])]
              (sut/do1
               (swap! side-effect-place conj 1)
               (swap! side-effect-place conj 2)
               (swap! side-effect-place conj 3))
              => anything
              (fact
               @side-effect-place => [1 2 3]))))

;;;; ___________________________________________________________________________
;;;; ---- sut/do2 ----

(fact "`sut/do2` works"

      (fact "Fails to compile when there are no forms"
            (macroexpand-1 '(sut/do2))
            => (throws clojure.lang.ArityException))

      (fact "Fails to compile when there is one forms"
            (macroexpand-1 '(sut/do2 :a))
            => (throws clojure.lang.ArityException))

      (fact "Returns value of second form when there are two forms"
            (sut/do2
             :a
             :b)
            => :b)

      (fact "Returns value of second form when there are three forms"
            (sut/do2
             :a
             :b
             :c)
            => :b)

      (fact "Forms are evaluated in correct order"
            (let [side-effect-place (atom [])]
              (sut/do2
               (swap! side-effect-place conj 1)
               (swap! side-effect-place conj 2)
               (swap! side-effect-place conj 3))
              => anything
              (fact
               @side-effect-place => [1 2 3]))))

;;;; ___________________________________________________________________________
;;;; ---- sut/econd ----

(fact "`sut/econd` works"
      (fact "no clauses"
            (sut/econd)
            => (throws RuntimeException))
      (fact "many clauses"
            (fact "last clause truthy"
                  (sut/econd false 1
                             nil   2
                             :this-one 3)
                  => 3)
            (fact "non-last clause truthy"
                  (sut/econd false 1
                             nil   2
                             :this-one 3
                             :not-this-one 4)
                  => 3)
            (fact "none truthy"
                  (sut/econd false 1
                             nil   2
                             false 3
                             nil   4)
                  => (throws RuntimeException))))
