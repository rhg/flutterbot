# Maintainer: Ricardo Gomez <ricardo.gomez331@gmail.com>
pkgname=flutterbot
pkgver=git
pkgrel=1
pkgdesc="an mlp-inspired bot based on lazybot"
arch=(x86_64 i686)
url="github.com/rhg/flutterbot"
license=('EPL')
depends=(mongodb)
makedepends=('leiningen')
source=(git://github.com/rhg/$pkgname.git
        flutterbot.sh
        flutterbot.service)
md5sums=('SKIP'
         '0af52abb1eb3a5acf91b27d3a4680f72'
         '7c2f596d2d3371a30d7937329aa0025e')
build() {
	cd "$srcdir/$pkgname"
        lein uberjar
}

package() {
	cd "$srcdir/$pkgname"
        install -Dm755 target/lazybot.jar "$pkgdir/opt/flutterbot.jar
        groupadd flutterbot
        useradd -d /var/lib/flutterbot -m -g flutterbot flutterbot
        install -Dm755 "$srcdir/flutterbot.sh" "$pkgdir/usr/bin/flutterbot
        install -Dm755 "$srcdir/flutterbot.service" "$pkgdir/etc/systemd/system/flutterbot.service"
}
