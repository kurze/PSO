
#set terminal postscript eps enhanced rounded color colortext lw 2 dl 4 20
#set terminal postscript eps enhanced rounded lw 2 dl 4 20
set terminal png nocrop medium 
#set key below
#set key off

set key title ""
set key inside right top vertical Right noreverse enhanced autotitles nobox
set key noinvert samplen 4 spacing 1 width 0 height 0 

set style data lines
set style function lines
set encoding utf8
set ylabel "fitness"

set terminal pngcairo size 800,600 enhanced font 'Verdana,10'


set xlabel "#evaluation"

set output "swarm-10-1-eval.png"
plot \
     "swarm-10-1-eval.dat" using 1:4:5 with filledcurve  title "swarm mean+/-stddev" lc rgb "#66CDAA", \
     "swarm-10-1-eval.dat" using 1:2 with lines title "swarm mean" lt 1 lw 2 linecolor rgb "#0000CD"

set xlabel "time (ms)"
set output "swarm-10-1-elapsed.png"
plot \
     "swarm-10-1-elapsed.dat" using 1:4:5 with filledcurve  title "swarm mean+/-stddev" lc rgb "#66CDAA", \
     "swarm-10-1-elapsed.dat" using 1:2 with lines title "swarm mean" lt 1 lw 2 linecolor rgb "#0000CD"

     
set xlabel "#evaluation"
set output "swarmvsrs-10-1-eval.png"
plot \
     "swarm-10-1-eval.dat" using 1:2 with lines title "swarm mean" lt 1 lw 2 linecolor rgb "#FF0000", \
     "colorrs-10-1-example-eval.dat" using 1:2 with lines title "rs mean" lt 1 lw 2 linecolor rgb "#00FF00"
     
