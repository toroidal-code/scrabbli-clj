(ns scrabbli-clj.maps)

; 0 points: blank *2
; 1 point: E *12, A *9, I *9, O *8, N *6, R *6, T *6, L *4, S *4, U *4
; 2 points: D *4, G *3
; 3 points: B *2, C *2, M *2, P *2
; 4 points: F *2, H *2, V *2, W *2, Y *2
; 5 points: K *1
; 8 points: J *1, X *1
; 10 points: Q *1, Z *1
; ==
; 100 total tiles
; 187 total points

(def SCORES (hash-map :a 1 :b 3 :c 3 :d 2 :e 1 :f 4 :g 2 :h  4 :i 1
                      :j 8 :k 5 :l 1 :m 3 :n 1 :o 1 :p 3 :q 10 :r 1
                      :s 1 :t 1 :u 1 :v 4 :w 4 :x 8 :y 4 :z 10 :_ 0))

(def TILES  (hash-map :a 9 :b 2 :c 2 :d 4 :e 12 :f 2 :g 3 :h 2 :i 9
                      :j 1 :k 1 :l 4 :m 2 :n  6 :o 8 :p 2 :q 1 :r 6
                      :s 4 :t 6 :u 4 :v 2 :w  2 :x 1 :y 2 :z 1 :_ 2))

;; T triple word
;; t triple letter
;; D double word
;; d double letter
(def BOARD
  [[  :T "." "."  :d "." "." "."  :T "." "." "."  :d "." "."  :T]
   [ "."  :D "." "." "."  :t "." "." "."  :t "." "." "."  :D "."]
   [ "." "."  :D "." "." "."  :d "."  :d "." "." "."  :D "." "."]
   [  :d "." "."  :D "." "." "."  :d "." "." "."  :D "." "."  :d]
   [ "." "." "." "."  :D "." "." "." "." "."  :D "." "." "." "."]
   [ "."  :t "." "." "."  :t "." "." "."  :t "." "." "."  :t "."]
   [ "." "."  :d "." "." "."  :d "."  :d "." "." "."  :d "." "."]
   [  :T "." "."  :d "." "." "."  :D "." "." "."  :d "." "."  :T]
   [ "." "."  :d "." "." "."  :d "."  :d "." "." "."  :d "." "."]
   [ "."  :t "." "." "."  :t "." "." "."  :t "." "." "."  :t "."]
   [ "." "." "." "."  :D "." "." "." "." "."  :D "." "." "." "."]
   [  :d "." "."  :D "." "." "."  :d "." "." "."  :D "." "."  :d]
   [ "." "."  :D "." "." "."  :d "."  :d "." "." "."  :D "." "."]
   [ "."  :D "." "." "."  :t "." "." "."  :t "." "." "."  :D "."]
   [  :T "." "."  :d "." "." "."  :T "." "." "."  :d "." "."  :T]])