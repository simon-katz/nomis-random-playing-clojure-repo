(ns nomis-random-playing-clojure-repo.core)

;;;; ___________________________________________________________________________
;;;; ---- do1 ----

(defmacro do1
  "Evaluates all the forms and returns the result of the first form."
  {:style/indent 1}
  [form-1 & other-forms]
  `(let [result# ~form-1]
     ~@other-forms
     result#))

;;;; ___________________________________________________________________________
;;;; ---- do2 ----

(defmacro do2
  "Evaluates all the forms and returns the result of the second form."
  {:style/indent 2}
  [form-1 form-2 & other-forms]
  `(do
     ~form-1
     (do1
         ~form-2
       ~@other-forms)))

;;;; ___________________________________________________________________________
;;;; ---- econd ----

(defmacro econd
  "Like `cond`, except throws a RuntimeException if no clause matches."
  [& clauses]
  `(cond ~@clauses
         :else (throw (RuntimeException. "econd has no matching clause"))))
