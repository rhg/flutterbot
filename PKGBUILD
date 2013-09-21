# Maintainer: Ricardo Gomez <ricardo.gomez331@gmail.com>
pkgname=flutterbot
pkgver=git
pkgrel=4
pkgdesc="an mlp-inspired bot based on lazybot"
arch=(x86_64 i686)
url="github.com/rhg/flutterbot"
license=('EPL')
depends=(mongodb leiningen)
install=flutterbot.install
source=(git://github.com/rhg/$pkgname.git#branch=flutterbot
        flutterbot.sh
        flutterbot.service)

md5sums=('SKIP'
         '51f035d53809e38c3b91e95711d22ced'
         'f5fec5100a6f62bb97c0a6baa8a292cd')

package() {
	cd "$srcdir/$pkgname"
	mkdir "$pkgdir/opt"
	mv "$srcdir/$pkgname" "$pkgdir/opt/flutterbot"
        install -Dm775 "$srcdir/flutterbot.sh" "$pkgdir/usr/bin/flutterbot"
        install -Dm775 "$srcdir/flutterbot.service" "$pkgdir/etc/systemd/system/flutterbot.service"
	chown -R 171:171 "$pkgdir/opt/flutterbot"
	chmod -R 775 "$pkgdir/opt/flutterbot"
}
