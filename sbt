#!/bin/bash

# taken and modified from https://raw.github.com/twitter/finagle/master/sbt
# Apache 2.0 License https://github.com/twitter/finagle/blob/master/LICENSE

root=$(
  cd $(dirname $(readlink $0 || echo $0))/..
  /bin/pwd
)

sbtver=0.13.0
sbtmd5=e5a228e929ec4ae7f762b77f831c9520
sbtjar=sbt-launch.jar

if [ ! -f $sbtjar ]; then
  echo 'downloading '$sbtjar 1>&2
  curl -O http://typesafe.artifactoryonline.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/$sbtver/$sbtjar
fi

test -f $sbtjar || exit 1
sbtjar_md5=$(openssl md5 < $sbtjar|cut -f2 -d'='|awk '{print $1}')
if [ "${sbtjar_md5}" != "$sbtmd5" ]; then
  echo 'bad sbtjar!' 1>&2
  exit 1
fi


test -f ~/.sbtconfig && . ~/.sbtconfig

java -ea                          \
  $SBT_OPTS                       \
  $JAVA_OPTS                      \
  -Djava.net.preferIPv4Stack=true \
  -XX:+AggressiveOpts             \
  -XX:+UseParNewGC                \
  -XX:+UseConcMarkSweepGC         \
  -XX:+CMSParallelRemarkEnabled   \
  -XX:+CMSClassUnloadingEnabled   \
  -XX:MaxPermSize=1024m           \
  -XX:SurvivorRatio=128           \
  -XX:MaxTenuringThreshold=0      \
  -XX:ReservedCodeCacheSize=128m  \
  -Xss8M                          \
  -Xms512M                        \
  -Xmx1G                          \
  -server                         \
  -jar $sbtjar "$@"