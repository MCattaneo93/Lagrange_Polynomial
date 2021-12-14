# Lagrange_Polynomial
Datamining Project - Lagrange Polynomials

1) For the Lagrange Polynomial code, one of the main comments raised was the inclusion of "error-checking" or the ability to see how the lagrange approximation works for known values.  I have implemented this in the code, however for both the individual approximations and the generalizations, the values approximated are not equivalent to the known values.  The code in it's entirety needs to be re-worked and cleaned up. 

2) No outside code was used from others.  The input information was taken from the Midterm 2 Assessment, however as long as the input file follows the format of the input file, then it will work, with any input file as long as the name is provided either through command line arguments, or has the name "testinput.txt". 

3) The Problem to solve is the Lagrange Polynomial Interpolation, or  Lagrange Multiplier mathematical pattern.  The software is finding unknown values by using the Lagrange Polynomials on a set of variables with given values to determine a pattern and output an apprroximate missing value(s).  The program follows the following mathematical equation: 
 ![image](https://user-images.githubusercontent.com/24501650/146005163-dfda3027-6e91-491f-92d1-4414ae7c18b8.png)
 
 Status of Code: The code is working, but not completed as it is not correct.  For the input file currently, the output is as follows:
 
(x0^1-0.427)(x0^1-0.191)(x0^1-0.156) * 13.283/-0.000095976 + (x0^1-0.191)(x0^1-0.156)(x0^1-0.148) * 21.053/0.017843724 + (x0^1-0.156)(x0^1-0.148)(x0^1-0.427) * 3.507/-0.000355180 + (x0^1-0.148)(x0^1-0.427)(x0^1-0.191) * 15.006/0.000075880
(x1^1-4.621)(x1^1-6.115)(x1^1-8.401) * 13.283/3.930208145 + (x1^1-6.115)(x1^1-8.401)(x1^1-8.76) * 21.053/-23.374257480 + (x1^1-8.401)(x1^1-8.76)(x1^1-4.621) * 3.507/9.033426180 + (x1^1-8.76)(x1^1-4.621)(x1^1-6.115) * 15.006/-3.102147720
(x2^1-72.191)(x2^1-42.621)(x2^1-54.954) * 13.283/563.573192600 + (x2^1-42.621)(x2^1-54.954)(x2^1-73.201) * 21.053/-514.795070900 + (x2^1-54.954)(x2^1-73.201)(x2^1-72.191) * 3.507/-11152.122649800 + (x2^1-73.201)(x2^1-72.191)(x2^1-42.621) * 15.006/3879.018806487

x0:
3872.4701
17902.7300
14.5785
8831.4511

x1:
6.2099
14.2093
13.8159
-13.9179

x2:
39.2593
1016.3677
-162.1559
4.2214

Generalized: 
9183.8582
2782980.4767
-61.8794
-477574.3641

Error Check: 

(x0^1-0.191)(x0^1-0.156) * 21.053/0.063956 + (x0^1-0.156)(x0^1-0.427) * 3.507/-0.008260 + (x0^1-0.427)(x0^1-0.191) * 15.006/0.009485
(x1^1-6.115)(x1^1-8.401) * 21.053/5.647320 + (x1^1-8.401)(x1^1-4.621) * 3.507/-3.415284 + (x1^1-4.621)(x1^1-6.115) * 15.006/8.641080
(x2^1-42.621)(x2^1-54.954) * 21.053/509.698090 + (x2^1-54.954)(x2^1-72.191) * 3.507/364.686810 + (x2^1-72.191)(x2^1-42.621) * 15.006/-212.583921

x0:
239.3488
684.1791
16.1611
420.9678

x1:
9.5129
18.5764
7.4594
49.6815

x2:
20.6967
-50.1924
18.6859
12.5547

Generalized: 
22.1892
-6184.0256
-22.2492
864.3428

(x0^1-0.191)(x0^1-0.156) * 13.283/0.000344 + (x0^1-0.156)(x0^1-0.148) * 3.507/0.001505 + (x0^1-0.148)(x0^1-0.191) * 15.006/-0.000280
(x1^1-6.115)(x1^1-8.401) * 13.283/0.949555 + (x1^1-8.401)(x1^1-8.76) * 3.507/6.046470 + (x1^1-8.76)(x1^1-6.115) * 15.006/-0.820674
(x2^1-42.621)(x2^1-54.954) * 13.283/557.993260 + (x2^1-54.954)(x2^1-73.201) * 3.507/377.143140 + (x2^1-73.201)(x2^1-42.621) * 15.006/-225.040251

x0:
-3571.3311
-8212.0723
14.5495
-5519.6013

x1:
-8.1958
14.0587
15.7433
0.4505

x2:
15.9707
-68.1797
2.4863
12.9655

Generalized: 
38889.0017
150608.3500
-47.2757
106745.8940

(x0^1-0.427)(x0^1-0.156) * 13.283/0.002232 + (x0^1-0.156)(x0^1-0.148) * 21.053/0.075609 + (x0^1-0.148)(x0^1-0.427) * 15.006/-0.002168
(x1^1-4.621)(x1^1-8.401) * 13.283/1.485901 + (x1^1-8.401)(x1^1-8.76) * 21.053/15.645420 + (x1^1-8.76)(x1^1-4.621) * 15.006/-1.357020
(x2^1-72.191)(x2^1-54.954) * 13.283/18.429470 + (x2^1-54.954)(x2^1-73.201) * 21.053/-17.409370 + (x2^1-73.201)(x2^1-72.191) * 15.006/314.523539

x0:
-71.8548
-269.9417
14.3703
-152.1713

x1:
21.6132
13.9690
17.4552
6.0219

x2:
42.8677
-1125.9695
-116.7075
-15.5077

Generalized: 
3874.2250
200564.6602
-102.2951
44140.1842

(x0^1-0.427)(x0^1-0.191) * 13.283/0.011997 + (x0^1-0.191)(x0^1-0.148) * 21.053/0.065844 + (x0^1-0.148)(x0^1-0.427) * 3.507/-0.010148 + 
(x1^1-4.621)(x1^1-6.115) * 13.283/10.947655 + (x1^1-6.115)(x1^1-8.76) * 21.053/6.183666 + (x1^1-8.76)(x1^1-4.621) * 3.507/-3.951630 + 
(x2^1-72.191)(x2^1-42.621) * 13.283/30.885800 + (x2^1-42.621)(x2^1-73.201) * 21.053/-29.865700 + (x2^1-73.201)(x2^1-72.191) * 3.507/904.250600 + 

x0:
185.2241
514.3289
11.9408
319.9410

x1:
9.9071
11.4823
3.6813
35.1109

x2:
46.2213
-506.4894
-97.0485
59.3134

Generalized: 
170.7637
25606.4889
83.6999
-2386.1299

For the first row of values, where f = 13.283, the generalized formula gives f = 22.  Also, the approximate values for x0 is supposed to be .148, but the program gives a value of 239.3488.

Limitations: The Lagrange Polynomial Approximation is an approximation, the values vary when comparing f given by x0, x1, or x2. For this program in particular, the current limitations include the generalization (as it gives the wrong answer).  



