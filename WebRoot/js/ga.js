
(function () {
	var g = void 0, h = null, aa = encodeURIComponent, ba = decodeURIComponent, i = Math;
	function ca(a, b) {
		return a.name = b;
	}
	var k = "push", da = "load", l = "charAt", ea = "value", m = "indexOf", fa = "match", ga = "name", ha = "host", p = "toString", r = "length", s = "prototype", t = "split", u = "stopPropagation", ia = "scope", v = "location", w = "getString", x = "substring", ja = "navigator", y = "join", z = "toLowerCase", A;
	function ka(a, b) {
		switch (b) {
		  case 0:
			return "" + a;
		  case 1:
			return a * 1;
		  case 2:
			return !!a;
		  case 3:
			return a * 1000;
		}
		return a;
	}
	function B(a) {
		return g == a || "-" == a || "" == a;
	}
	function la(a) {
		if (!a || "" == a) {
			return "";
		}
		for (; a && " \n\r\t"[m](a[l](0)) > -1; ) {
			a = a[x](1);
		}
		for (; a && " \n\r\t"[m](a[l](a[r] - 1)) > -1; ) {
			a = a[x](0, a[r] - 1);
		}
		return a;
	}
	function ma(a) {
		var b = 1, c = 0, d;
		if (!B(a)) {
			b = 0;
			for (d = a[r] - 1; d >= 0; d--) {
				c = a.charCodeAt(d), b = (b << 6 & 268435455) + c + (c << 14), c = b & 266338304, b = c != 0 ? b ^ c >> 21 : b;
			}
		}
		return b;
	}
	function na() {
		return i.round(i.random() * 2147483647);
	}
	function oa() {
	}
	function C(a, b) {
		return aa instanceof Function ? b ? encodeURI(a) : aa(a) : (D(68), escape(a));
	}
	function F(a) {
		a = a[t]("+")[y](" ");
		if (ba instanceof Function) {
			try {
				return ba(a);
			}
			catch (b) {
				D(17);
			}
		} else {
			D(68);
		}
		return unescape(a);
	}
	var pa = function (a, b, c, d) {
		a.addEventListener ? a.addEventListener(b, c, !!d) : a.attachEvent && a.attachEvent("on" + b, c);
	};
	function qa(a) {
		return a && a[r] > 0 ? a[0] : "";
	}
	function ra(a) {
		var b = a ? a[r] : 0;
		return b > 0 ? a[b - 1] : "";
	}
	var sa = function () {
		this.prefix = "ga.";
		this.F = {};
	};
	sa[s].set = function (a, b) {
		this.F[this.prefix + a] = b;
	};
	sa[s].get = function (a) {
		return this.F[this.prefix + a];
	};
	sa[s].contains = function (a) {
		return this.get(a) !== g;
	};
	function ta(a) {
		a[m]("www.") == 0 && (a = a[x](4));
		return a[z]();
	}
	function ua(a, b) {
		var c, d = {url:a, protocol:"http", host:"", path:"", c:new sa, anchor:""};
		if (!a) {
			return d;
		}
		c = a[m]("://");
		if (c >= 0) {
			d.protocol = a[x](0, c), a = a[x](c + 3);
		}
		c = a.search("/|\\?|#");
		if (c >= 0) {
			d.host = a[x](0, c)[z](), a = a[x](c);
		} else {
			return d.host = a[z](), d;
		}
		c = a[m]("#");
		if (c >= 0) {
			d.anchor = a[x](c + 1), a = a[x](0, c);
		}
		c = a[m]("?");
		c >= 0 && (va(d.c, a[x](c + 1)), a = a[x](0, c));
		d.anchor && b && va(d.c, d.anchor);
		a && a[l](0) == "/" && (a = a[x](1));
		d.path = a;
		return d;
	}
	function va(a, b) {
		function c(b, c) {
			a.contains(b) || a.set(b, []);
			a.get(b)[k](c);
		}
		for (var d = la(b)[t]("&"), e = 0; e < d[r]; e++) {
			if (d[e]) {
				var f = d[e][m]("=");
				f < 0 ? c(d[e], "1") : c(d[e][x](0, f), d[e][x](f + 1));
			}
		}
	}
	function wa(a, b) {
		if (B(a)) {
			return "-";
		}
		if ("[" == a[l](0) && "]" == a[l](a[r] - 1)) {
			return "-";
		}
		var c = G.domain;
		c += b && b != "/" ? b : "";
		return a[m](c) == (a[m]("http://") == 0 ? 7 : a[m]("https://") == 0 ? 8 : 0) ? "0" : a;
	}
	var xa = 0;
	function H(a) {
		return (a ? "_" : "") + xa++;
	}
	var ya = H(), za = H(), Aa = H(), I = H(), J = H(), K = H(), L = H(), Ba = H(), Ca = H(), Da = H(), Ea = H(), Fa = H(), Ga = H(), Ha = H(), Ia = H(), Ja = H(), Ka = H(), La = H(), Ma = H(), Na = H(), Oa = H(), Pa = H(), Qa = H(), Ra = H(), Sa = H(), Ta = H(), Ua = H(), Va = H(), Wa = H(), Xa = H(), Ya = H(), Za = H(), $a = H(), ab = H(), bb = H();
	H();
	var M = H(!0), cb = H(), db = H(), eb = H(), fb = H(), gb = H(), hb = H(), ib = H(), jb = H(), kb = H(), lb = H(), N = H(!0), mb = H(!0), nb = H(!0), ob = H(!0), rb = H(!0), sb = H(!0), tb = H(!0), ub = H(!0), vb = H(!0), wb = H(!0), xb = H(!0), O = H(!0), yb = H(!0), zb = H(!0), Ab = H(!0), Bb = H(!0), Cb = H(!0), Db = H(!0), Eb = H(!0), Fb = H(!0), Gb = H(!0), Hb = H(!0), Ib = H(!0), Jb = H(!0), Kb = H(!0), Lb = H(), Mb = H();
	H();
	var Nb = H(), Ob = H(), Pb = H(), Qb = H(), Rb = H(), Ub = H(), Vb = H(), Wb = H();
	H();
	var Xb = H(), Yb = H();
	var Zb = function () {
		function a(a, c, d) {
			P(Q[s], a, c, d);
		}
		R("_getName", Aa, 58);
		R("_getAccount", ya, 64);
		R("_visitCode", N, 54);
		R("_getClientInfo", Ha, 53, 1);
		R("_getDetectTitle", Ka, 56, 1);
		R("_getDetectFlash", Ia, 65, 1);
		R("_getLocalGifPath", Ua, 57);
		R("_getServiceMode", Va, 59);
		S("_setClientInfo", Ha, 66, 2);
		S("_setAccount", ya, 3);
		S("_setNamespace", za, 48);
		S("_setAllowLinker", Ea, 11, 2);
		S("_setDetectFlash", Ia, 61, 2);
		S("_setDetectTitle", Ka, 62, 2);
		S("_setLocalGifPath", Ua, 46, 0);
		S("_setLocalServerMode", Va, 92, g, 0);
		S("_setRemoteServerMode", Va, 63, g, 1);
		S("_setLocalRemoteServerMode", Va, 47, g, 2);
		S("_setSampleRate", Ta, 45, 1);
		S("_setCampaignTrack", Ja, 36, 2);
		S("_setAllowAnchor", Fa, 7, 2);
		S("_setCampNameKey", Ma, 41);
		S("_setCampContentKey", Ra, 38);
		S("_setCampIdKey", La, 39);
		S("_setCampMediumKey", Pa, 40);
		S("_setCampNOKey", Sa, 42);
		S("_setCampSourceKey", Oa, 43);
		S("_setCampTermKey", Qa, 44);
		S("_setCampCIdKey", Na, 37);
		S("_setCookiePath", L, 9, 0);
		S("_setMaxCustomVariables", Wa, 0, 1);
		S("_setVisitorCookieTimeout", Ba, 28, 1);
		S("_setSessionCookieTimeout", Ca, 26, 1);
		S("_setCampaignCookieTimeout", Da, 29, 1);
		S("_setReferrerOverride", eb, 49);
		a("_trackPageview", Q[s].ka, 1);
		a("_trackEvent", Q[s].t, 4);
		a("_trackSocial", Q[s].la, 104);
		a("_trackPageLoadTime", Q[s].ja, 100);
		a("_trackTrans", Q[s].ma, 18);
		a("_sendXEvent", Q[s].s, 78);
		a("_createEventTracker", Q[s].S, 74);
		a("_getVersion", Q[s].X, 60);
		a("_setDomainName", Q[s].r, 6);
		a("_setAllowHash", Q[s].ba, 8);
		a("_getLinkerUrl", Q[s].W, 52);
		a("_link", Q[s].link, 101);
		a("_linkByPost", Q[s].aa, 102);
		a("_setTrans", Q[s].ea, 20);
		a("_addTrans", Q[s].L, 21);
		a("_addItem", Q[s].J, 19);
		a("_setTransactionDelim", Q[s].fa, 82);
		a("_setCustomVar", Q[s].ca, 10);
		a("_deleteCustomVar", Q[s].U, 35);
		a("_getVisitorCustomVar", Q[s].Y, 50);
		a("_setXKey", Q[s].ha, 83);
		a("_setXValue", Q[s].ia, 84);
		a("_getXKey", Q[s].Z, 76);
		a("_getXValue", Q[s].$, 77);
		a("_clearXKey", Q[s].P, 72);
		a("_clearXValue", Q[s].Q, 73);
		a("_createXObj", Q[s].T, 75);
		a("_addIgnoredOrganic", Q[s].H, 15);
		a("_clearIgnoredOrganic", Q[s].M, 97);
		a("_addIgnoredRef", Q[s].I, 31);
		a("_clearIgnoredRef", Q[s].N, 32);
		a("_addOrganic", Q[s].K, 14);
		a("_clearOrganic", Q[s].O, 70);
		a("_cookiePathCopy", Q[s].R, 30);
		a("_get", Q[s].V, 106);
		a("_set", Q[s].da, 107);
		a("_addEventListener", Q[s].addEventListener, 108);
		a("_removeEventListener", Q[s].removeEventListener, 109);
		a("_initData", Q[s].l, 2);
		a("_setVar", Q[s].ga, 22);
		S("_setSessionTimeout", Ca, 27, 3);
		S("_setCookieTimeout", Da, 25, 3);
		S("_setCookiePersistence", Ba, 24, 1);
		a("_setAutoTrackOutbound", oa, 79);
		a("_setTrackOutboundSubdomains", oa, 81);
		a("_setHrefExamineLimit", oa, 80);
	}, P = function (a, b, c, d) {
		a[b] = function () {
			D(d);
			return c.apply(this, arguments);
		};
	}, R = function (a, b, c, d) {
		Q[s][a] = function () {
			D(c);
			return ka(this.a.get(b), d);
		};
	}, S = function (a, b, c, d, e) {
		Q[s][a] = function (a) {
			D(c);
			e == g ? this.a.set(b, ka(a, d)) : this.a.set(b, e);
		};
	}, $b = function (a, b) {
		return {type:b, target:a, stopPropagation:function () {
			throw "aborted";
		}};
	};
	var ac = function (a, b) {
		return b !== "/" ? !1 : (a[m]("www.google.") == 0 || a[m](".google.") == 0 || a[m]("google.") == 0) && !(a[m]("google.org") > -1) ? !0 : !1;
	}, bc = function (a) {
		var b = a.get(J), c = a[w](L, "/");
		ac(b, c) && a[u]();
	};
	var fc = function () {
		var a = {}, b = {}, c = new cc;
		this.h = function (a, b) {
			c.add(a, b);
		};
		var d = new cc;
		this.d = function (a, b) {
			d.add(a, b);
		};
		var e = !1, f = !1, j = !0;
		this.G = function () {
			e = !0;
		};
		this.f = function (a) {
			this[da]();
			this.set(Lb, a, !0);
			e = !1;
			d.execute(this);
			e = !0;
			b = {};
			this.i();
		};
		this.load = function () {
			e && (e = !1, this.na(), dc(this), f || (f = !0, c.execute(this), ec(this), dc(this)), e = !0);
		};
		this.i = function () {
			if (e) {
				if (f) {
					e = !1, ec(this), e = !0;
				} else {
					this[da]();
				}
			}
		};
		this.get = function (c) {
			c && c[l](0) == "_" && this[da]();
			return b[c] !== g ? b[c] : a[c];
		};
		this.set = function (c, d, e) {
			c && c[l](0) == "_" && this[da]();
			e ? b[c] = d : a[c] = d;
			c && c[l](0) == "_" && this.i();
		};
		this.m = function (b) {
			a[b] = this.b(b, 0) + 1;
		};
		this.b = function (a, b) {
			var c = this.get(a);
			return c == g || c === "" ? b : c * 1;
		};
		this.getString = function (a, b) {
			var c = this.get(a);
			return c == g ? b : c + "";
		};
		this.na = function () {
			if (j) {
				var b = this[w](J, ""), c = this[w](L, "/");
				ac(b, c) || (a[K] = a[Ga] && b != "" ? ma(b) : 1, j = !1);
			}
		};
	};
	fc[s].stopPropagation = function () {
		throw "aborted";
	};
	function T(a, b) {
		for (var b = b || [], c = 0; c < b[r]; c++) {
			var d = b[c];
			if ("" + a == d || d[m](a + ".") == 0) {
				return d;
			}
		}
		return "-";
	}
	var gc = function (a, b) {
		var c = a.b(K, 1), d = b[t](".");
		if (d[r] !== 6 || d[0] != c) {
			return !1;
		}
		var c = d[1] * 1, e = d[2] * 1, f = d[3] * 1, j = d[4] * 1, d = d[5] * 1;
		if (!(c >= 0 && e > 0 && f > 0 && j > 0 && d >= 0)) {
			return D(110), !1;
		}
		a.set(N, c);
		a.set(rb, e);
		a.set(sb, f);
		a.set(tb, j);
		a.set(ub, d);
		return !0;
	}, hc = function (a) {
		var b = a.get(N), c = a.get(rb), d = a.get(sb), e = a.get(tb), f = a.b(ub, 1);
		b == g ? D(113) : b == NaN && D(114);
		b >= 0 && c > 0 && d > 0 && e > 0 && f >= 0 || D(115);
		return [a.b(K, 1), b != g ? b : "-", c || "-", d || "-", e || "-", f][y](".");
	}, ic = function (a) {
		return [a.b(K, 1), a.b(xb, 0), a.b(O, 1), a.b(yb, 0)][y](".");
	}, jc = function (a, b) {
		var c = b[t]("."), d = a.b(K, 1);
		if (c[r] !== 4 || c[0] != d) {
			c = h;
		}
		a.set(xb, c ? c[1] * 1 : 0);
		a.set(O, c ? c[2] * 1 : 10);
		a.set(yb, c ? c[3] * 1 : a.get(I));
		return c != h || b == d;
	}, kc = function (a, b) {
		var c = C(a[w](nb, "")), d = [], e = a.get(M);
		if (!b && e) {
			for (var f = 0; f < e[r]; f++) {
				var j = e[f];
				j && j[ia] == 1 && d[k](f + "=" + C(j[ga]) + "=" + C(j[ea]) + "=1");
			}
			d[r] > 0 && (c += "|" + d[y](","));
		}
		return c ? a.b(K, 1) + "." + c : h;
	}, lc = function (a, b) {
		var c = a.b(K, 1), d = b[t](".");
		if (d[r] < 2 || d[0] != c) {
			return !1;
		}
		c = d.slice(1)[y](".")[t]("|");
		c[r] > 0 && a.set(nb, F(c[0]));
		if (c[r] <= 1) {
			return !0;
		}
		for (var d = c[1][t](","), e = 0; e < d[r]; e++) {
			var f = d[e][t]("=");
			if (f[r] == 4) {
				var j = {};
				ca(j, F(f[1]));
				j.value = F(f[2]);
				j.scope = 1;
				a.get(M)[f[0]] = j;
			}
		}
		c[1][m]("^") >= 0 && D(125);
		return !0;
	}, nc = function (a, b) {
		var c = mc(a, b);
		return c ? [a.b(K, 1), a.b(zb, 0), a.b(Ab, 1), a.b(Bb, 1), c][y](".") : "";
	}, mc = function (a) {
		function b(b, e) {
			if (!B(a.get(b))) {
				var f = a[w](b, ""), f = f[t](" ")[y]("%20"), f = f[t]("+")[y]("%20");
				c[k](e + "=" + f);
			}
		}
		var c = [];
		b(Db, "utmcid");
		b(Hb, "utmcsr");
		b(Fb, "utmgclid");
		b(Gb, "utmdclid");
		b(Eb, "utmccn");
		b(Ib, "utmcmd");
		b(Jb, "utmctr");
		b(Kb, "utmcct");
		return c[y]("|");
	}, pc = function (a, b) {
		var c = a.b(K, 1), d = b[t](".");
		if (d[r] < 5 || d[0] != c) {
			return a.set(zb, g), a.set(Ab, g), a.set(Bb, g), a.set(Db, g), a.set(Eb, g), a.set(Hb, g), a.set(Ib, g), a.set(Jb, g), a.set(Kb, g), a.set(Fb, g), a.set(Gb, g), !1;
		}
		a.set(zb, d[1] * 1);
		a.set(Ab, d[2] * 1);
		a.set(Bb, d[3] * 1);
		oc(a, d.slice(4)[y]("."));
		return !0;
	}, oc = function (a, b) {
		function c(a) {
			return (a = b[fa](a + "=(.*?)(?:\\|utm|$)")) && a[r] == 2 ? a[1] : g;
		}
		function d(b, c) {
			c && (c = e ? F(c) : c[t]("%20")[y](" "), a.set(b, c));
		}
		b[m]("=") == -1 && (b = F(b));
		var e = c("utmcvr") == "2";
		d(Db, c("utmcid"));
		d(Eb, c("utmccn"));
		d(Hb, c("utmcsr"));
		d(Ib, c("utmcmd"));
		d(Jb, c("utmctr"));
		d(Kb, c("utmcct"));
		d(Fb, c("utmgclid"));
		d(Gb, c("utmdclid"));
	};
	var cc = function () {
		this.q = [];
	};
	cc[s].add = function (a, b) {
		this.q[k]({name:a, ua:b});
	};
	cc[s].execute = function (a) {
		try {
			for (var b = 0; b < this.q[r]; b++) {
				this.q[b].ua.call(U, a);
			}
		}
		catch (c) {
		}
	};
	function qc(a) {
		a.get(Ta) != 100 && a.get(N) % 10000 >= a.get(Ta) * 100 && a[u]();
	}
	function rc(a) {
		sc() && a[u]();
	}
	function tc(a) {
		G[v].protocol == "file:" && a[u]();
	}
	function uc(a) {
		a.get(db) || a.set(db, G.title, !0);
		a.get(cb) || a.set(cb, G[v].pathname + G[v].search, !0);
	}
	var vc = new function () {
		var a = [];
		this.set = function (b) {
			a[b] = !0;
		};
		this.va = function () {
			for (var b = [], c = 0; c < a[r]; c++) {
				a[c] && (b[i.floor(c / 6)] ^= 1 << c % 6);
			}
			for (c = 0; c < b[r]; c++) {
				b[c] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_"[l](b[c] || 0);
			}
			return b[y]("") + "~";
		};
	};
	function D(a) {
		vc.set(a);
	}
	var U = window, G = document, sc = function () {
		var a = U._gaUserPrefs;
		return a && a.ioo && a.ioo();
	}, wc = function (a, b) {
		setTimeout(a, b);
	}, V = function (a) {
		for (var b = [], c = G.cookie[t](";"), a = RegExp("\\s*" + a + "=\\s*(.*?)\\s*$"), d = 0; d < c[r]; d++) {
			var e = c[d][fa](a);
			e && b[k](e[1]);
		}
		return b;
	}, W = function (a, b, c, d, e) {
		var f;
		f = sc() ? !1 : ac(d, c) ? !1 : !0;
		if (f) {
			if (b && U[ja].userAgent[m]("Firefox") >= 0) {
				b = b.replace(/\n|\r/g, " ");
				f = 0;
				for (var j = b[r]; f < j; ++f) {
					var o = b.charCodeAt(f) & 255;
					if (o == 10 || o == 13) {
						b = b[x](0, f) + "?" + b[x](f + 1);
					}
				}
			}
			b && b[r] > 2000 && (b = b[x](0, 2000), D(69));
			a = a + "=" + b + "; path=" + c + "; ";
			e && (a += "expires=" + (new Date((new Date).getTime() + e)).toGMTString() + "; ");
			d && (a += "domain=" + d + ";");
			G.cookie = a;
		}
	};
	var xc, yc, zc = function () {
		if (!xc) {
			var a = {}, b = U[ja], c = U.screen;
			a.C = c ? c.width + "x" + c.height : "-";
			a.B = c ? c.colorDepth + "-bit" : "-";
			a.language = (b && (b.language || b.browserLanguage) || "-")[z]();
			a.javaEnabled = b && b.javaEnabled() ? 1 : 0;
			a.characterSet = G.characterSet || G.charset || "-";
			xc = a;
		}
	}, Ac = function () {
		zc();
		for (var a = xc, b = U[ja], a = b.appName + b.version + a.language + b.platform + b.userAgent + a.javaEnabled + a.C + a.B + (G.cookie ? G.cookie : "") + (G.referrer ? G.referrer : ""), b = a[r], c = U.history[r]; c > 0; ) {
			a += c-- ^ b++;
		}
		return ma(a);
	}, Bc = function (a) {
		zc();
		var b = xc;
		a.set(gb, b.C);
		a.set(hb, b.B);
		a.set(kb, b.language);
		a.set(lb, b.characterSet);
		a.set(ib, b.javaEnabled);
		if (a.get(Ha) && a.get(Ia)) {
			if (!(b = yc)) {
				var c, d, e;
				d = "ShockwaveFlash";
				if ((b = (b = U[ja]) ? b.plugins : g) && b[r] > 0) {
					for (c = 0; c < b[r] && !e; c++) {
						d = b[c], d[ga][m]("Shockwave Flash") > -1 && (e = d.description[t]("Shockwave Flash ")[1]);
					}
				} else {
					d = d + "." + d;
					try {
						c = new ActiveXObject(d + ".7"), e = c.GetVariable("$version");
					}
					catch (f) {
					}
					if (!e) {
						try {
							c = new ActiveXObject(d + ".6"), e = "WIN 6,0,21,0", c.AllowScriptAccess = "always", e = c.GetVariable("$version");
						}
						catch (j) {
						}
					}
					if (!e) {
						try {
							c = new ActiveXObject(d), e = c.GetVariable("$version");
						}
						catch (o) {
						}
					}
					e && (e = e[t](" ")[1][t](","), e = e[0] + "." + e[1] + " r" + e[2]);
				}
				b = e ? e : "-";
			}
			yc = b;
			a.set(jb, yc);
		} else {
			a.set(jb, "-");
		}
	};
	var Y = function () {
		P(Y[s], "push", Y[s][k], 5);
		P(Y[s], "_createAsyncTracker", Y[s].wa, 33);
		P(Y[s], "_getAsyncTracker", Y[s].xa, 34);
	};
	Y[s].wa = function (a, b) {
		return Z.k(a, b || "");
	};
	Y[s].xa = function (a) {
		return Z.p(a);
	};
	Y[s].push = function (a) {
		for (var b = arguments, c = 0, d = 0; d < b[r]; d++) {
			try {
				if (typeof b[d] === "function") {
					b[d]();
				} else {
					var e = "", f = b[d][0], j = f.lastIndexOf(".");
					j > 0 && (e = f[x](0, j), f = f[x](j + 1));
					var o = e == "_gat" ? Z : e == "_gaq" ? Cc : Z.p(e);
					o[f].apply(o, b[d].slice(1));
				}
			}
			catch (n) {
				c++;
			}
		}
		return c;
	};
	var Fc = function () {
		function a(a, b, c, d) {
			g == f[a] && (f[a] = {});
			g == f[a][b] && (f[a][b] = []);
			f[a][b][c] = d;
		}
		function b(a, b, c) {
			if (g != f[a] && g != f[a][b]) {
				return f[a][b][c];
			}
		}
		function c(a, b) {
			if (g != f[a] && g != f[a][b]) {
				f[a][b] = g;
				var c = !0, d;
				for (d = 0; d < j[r]; d++) {
					if (g != f[a][j[d]]) {
						c = !1;
						break;
					}
				}
				c && (f[a] = g);
			}
		}
		function d(a) {
			var b = "", c = !1, d, e;
			for (d = 0; d < j[r]; d++) {
				if (e = a[j[d]], g != e) {
					c && (b += j[d]);
					for (var c = [], f = g, X = g, X = 0; X < e[r]; X++) {
						if (g != e[X]) {
							f = "";
							X != ld && g == e[X - 1] && (f += X[p]() + pb);
							for (var Jc = e[X], Kc = "", qb = g, Sb = g, Tb = g, qb = 0; qb < Jc[r]; qb++) {
								Sb = Jc[l](qb), Tb = E[Sb], Kc += g != Tb ? Tb : Sb;
							}
							f += Kc;
							c[k](f);
						}
					}
					b += o + c[y](q) + n;
					c = !1;
				} else {
					c = !0;
				}
			}
			return b;
		}
		var e = this, f = [], j = ["k", "v"], o = "(", n = ")", q = "*", pb = "!", E = {"'":"'0"};
		E[n] = "'1";
		E[q] = "'2";
		E[pb] = "'3";
		var ld = 1;
		e.qa = function (a) {
			return g != f[a];
		};
		e.n = function () {
			for (var a = "", b = 0; b < f[r]; b++) {
				g != f[b] && (a += b[p]() + d(f[b]));
			}
			return a;
		};
		e.pa = function (a) {
			if (a == g) {
				return e.n();
			}
			for (var b = a.n(), c = 0; c < f[r]; c++) {
				g != f[c] && !a.qa(c) && (b += c[p]() + d(f[c]));
			}
			return b;
		};
		e.e = function (b, c, d) {
			if (!Dc(d)) {
				return !1;
			}
			a(b, "k", c, d);
			return !0;
		};
		e.j = function (b, c, d) {
			if (!Ec(d)) {
				return !1;
			}
			a(b, "v", c, d[p]());
			return !0;
		};
		e.w = function (a, c) {
			return b(a, "k", c);
		};
		e.z = function (a, c) {
			return b(a, "v", c);
		};
		e.u = function (a) {
			c(a, "k");
		};
		e.v = function (a) {
			c(a, "v");
		};
		P(e, "_setKey", e.e, 89);
		P(e, "_setValue", e.j, 90);
		P(e, "_getKey", e.w, 87);
		P(e, "_getValue", e.z, 88);
		P(e, "_clearKey", e.u, 85);
		P(e, "_clearValue", e.v, 86);
	};
	function Dc(a) {
		return typeof a == "string";
	}
	function Ec(a) {
		return typeof a != "number" && (g == Number || !(a instanceof Number)) || i.round(a) != a || a == NaN || a == Infinity ? !1 : !0;
	}
	var Gc = function (a) {
		var b = U.gaGlobal;
		a && !b && (U.gaGlobal = b = {});
		return b;
	}, Hc = function () {
		var a = Gc(!0).hid;
		if (a == h) {
			a = na(), Gc(!0).hid = a;
		}
		return a;
	}, Ic = function (a) {
		a.set(fb, Hc());
		var b = Gc();
		if (b && b.dh == a.get(K)) {
			var c = b.sid;
			c && (c == "0" && D(112), a.set(tb, c), a.get(mb) && a.set(sb, c));
			b = b.vid;
			a.get(mb) && b && (b = b[t]("."), b[1] * 1 || D(112), a.set(N, b[0] * 1), a.set(rb, b[1] * 1));
		}
	};
	var Lc, Mc = function (a, b, c) {
		var d = a[w](J, ""), e = a[w](L, "/"), a = a.b(Ba, 0);
		W(b, c, e, d, a);
	}, ec = function (a) {
		var b = a[w](J, "");
		a.b(K, 1);
		var c = a[w](L, "/");
		W("__utma", hc(a), c, b, a.get(Ba));
		W("__utmb", ic(a), c, b, a.get(Ca));
		W("__utmc", "" + a.b(K, 1), c, b);
		var d = nc(a, !0);
		d ? W("__utmz", d, c, b, a.get(Da)) : W("__utmz", "", c, b, -1);
		(d = kc(a, !1)) ? W("__utmv", d, c, b, a.get(Ba)) : W("__utmv", "", c, b, -1);
	}, dc = function (a) {
		var b = a.b(K, 1);
		if (!gc(a, T(b, V("__utma")))) {
			return a.set(ob, !0), !1;
		}
		var c = !jc(a, T(b, V("__utmb"))), d = T(b, V("__utmc")) != a.b(K, 1);
		d && !c && D(116);
		a.set(wb, c || d);
		pc(a, T(b, V("__utmz")));
		lc(a, T(b, V("__utmv")));
		Lc = !c;
		return !0;
	}, Nc = function (a) {
		!Lc && !(V("__utmb")[r] > 0) && (W("__utmd", "1", a[w](L, "/"), a[w](J, ""), 10000), V("__utmd")[r] == 0 && a[u]());
	};
	var Pc = function (a) {
		a.get(N) == g ? Oc(a) : a.get(ob) && !a.get(Xb) ? Oc(a) : a.get(wb) && (a.set(sb, a.get(tb)), a.set(tb, a.get(I)), a.m(ub), a.set(vb, !0), a.set(xb, 0), a.set(O, 10), a.set(yb, a.get(I)), a.set(wb, !1));
	}, Oc = function (a) {
		var b = a.get(I);
		a.set(mb, !0);
		a.set(N, na() ^ Ac(a) & 2147483647);
		a.set(nb, "");
		a.set(rb, b);
		a.set(sb, b);
		a.set(tb, b);
		a.set(ub, 1);
		a.set(vb, !0);
		a.set(xb, 0);
		a.set(O, 10);
		a.set(yb, b);
		a.set(M, []);
		a.set(ob, !1);
		a.set(wb, !1);
	};
	var Qc = "daum:q,eniro:search_word,naver:query,pchome:q,images.google:q,google:q,yahoo:p,yahoo:q,msn:q,bing:q,aol:query,aol:encquery,aol:q,lycos:query,ask:q,altavista:q,netscape:query,cnn:query,about:terms,mamma:q,alltheweb:q,voila:rdata,virgilio:qs,live:q,baidu:wd,alice:qs,yandex:text,najdi:q,mama:query,seznam:q,search:q,wp:szukaj,onet:qt,szukacz:q,yam:k,kvasir:q,sesam:q,ozu:q,terra:query,mynet:q,ekolay:q,rambler:query".split(","), Xc = function (a) {
		if (a.get(Ja) && !a.get(Xb)) {
			for (var b = Rc(a), c = {}, d = 0; d < Sc[r]; d++) {
				var e = Sc[d];
				c[e] = a.get(e);
			}
			!Tc(a) && !Uc(a) && !b && a.get(vb) && a.get(vb) && Vc(a, g, "(direct)", g, g, "(direct)", "(none)", g, g);
			a.set(Cb, Wc(a, c));
			b = a.get(Hb) == "(direct)" && a.get(Eb) == "(direct)" && a.get(Ib) == "(none)";
			if (a.get(Cb) || a.get(vb) && !b) {
				a.set(zb, a.get(I)), a.set(Ab, a.get(ub)), a.m(Bb);
			}
		}
	}, Tc = function (a) {
		function b(b, d) {
			var d = d || "-", e = ra(c.c.get(a.get(b)));
			return e && e != "-" ? F(e) : d;
		}
		var c = ua(G[v].href, a.get(Fa)), d = ra(c.c.get(a.get(La))) || "-", e = ra(c.c.get(a.get(Oa))) || "-", f = ra(c.c.get(a.get(Na))) || "-", j = ra(c.c.get("dclid")) || "-", o = b(Ma, "(not set)"), n = b(Pa, "(not set)"), q = b(Qa), pb = b(Ra), E = b(Sa);
		if (B(d) && B(f) && B(j) && B(e)) {
			return !1;
		}
		if (E == "1" && Rc(a)) {
			return !1;
		}
		B(q) && (E = wa(a.get(eb), a.get(L)), E = ua(E, !0), (E = Yc(a, E)) && !B(E[1] && !E[2]) && (q = E[1]));
		Vc(a, d, e, f, j, o, n, q, pb);
		return !0;
	}, Uc = function (a) {
		var b = wa(a.get(eb), a.get(L)), c = ua(b, !0);
		if (!(b != g && b != h && b != "" && b != "0" && b != "-" && b[m]("://") >= 0) || c && c[ha][m]("google") > -1 && c.c.contains("q") && c.path == "cse") {
			return !1;
		}
		if ((b = Yc(a, c)) && !b[2]) {
			return Vc(a, g, b[0], g, g, "(organic)", "organic", b[1], g), !0;
		} else {
			if (b) {
				return !1;
			}
		}
		if (a.get(vb)) {
		a:
			{for (var b = a.get(Za), d = ta(c[ha]), e = 0; e < b[r]; ++e) {
				if (d[m](b[e]) > -1) {
					a = !1;
					break a;
				}
			}
			Vc(a, g, d, g, g, "(referral)", "referral", g, "/" + c.path);
			a = !0;
		}
	} else {
		a = !1;
	}
	return a;
}, Yc = function (a, b) {
	for (var c = a.get(Xa), d = 0; d < c[r]; ++d) {
		var e = c[d][t](":");
		if (b[ha][m](e[0][z]()) > -1) {
			var f = qa(b.c.get(e[1]));
			if (f) {
			a:
				{for (var c = f, d = a.get(Ya), c = F(c)[z](), j = 0; j < d[r]; ++j) {
					if (c == d[j]) {
						c = !0;
						break a;
					}
				}
				c = !1;
			}
			return [e[0], f, c];
		}
	}
}
return h;
}, Vc = function (a, b, c, d, e, f, j, o, n) {
a.set(Db, b);
a.set(Hb, c);
a.set(Fb, d);
a.set(Gb, e);
a.set(Eb, f);
a.set(Ib, j);
a.set(Jb, o);
a.set(Kb, n);
}, Rc = function (a) {
return !B(a.get(Db)) || !B(a.get(Hb)) || !B(a.get(Fb)) || !B(a.get(Gb));
}, Sc = [Eb, Db, Fb, Gb, Hb, Ib, Jb, Kb], Wc = function (a, b) {
for (var c = 0; c < Sc[r]; c++) {
	var d = Sc[c], e = b[d] || "-", d = a.get(d) || "-";
	if (e != d) {
		return !0;
	}
}
return !1;
};
var $c = function (a) {
Zc(a, G[v].href) ? (a.set(Xb, !0), D(12)) : a.set(Xb, !1);
}, Zc = function (a, b) {
if (!a.get(Ea)) {
	return !1;
}
var c = a.b(K, 1), d = ua(b, a.get(Fa)), e = T(c, d.c.get("__utma")), f = T(c, d.c.get("__utmb")), j = T(c, d.c.get("__utmc")), o = T(c, d.c.get("__utmx")), n = T(c, d.c.get("__utmz")), q = T(c, d.c.get("__utmv")), d = qa(d.c.get("__utmk"));
if (ma("" + e + f + j + o + n + q) != d) {
	return !1;
}
if (!gc(a, e)) {
	return e && e[m](c + ".") != 0 && D(126), !1;
}
jc(a, f);
a.b(K, 1);
pc(a, n);
lc(a, q);
c = F(o);
e = a.b(K, 1);
f = c[t](".");
f[r] < 2 || f[0] != e || Mc(a, "__utmx", c);
return !0;
}, ad = function (a, b, c) {
var d;
a.b(K, 1);
d = hc(a) || "-";
var e = ic(a) || "-", f = "" + a.b(K, 1) || "-", j;
j = qa(V("__utmx")) || "-";
j = C(j);
var o = C(nc(a, !1)) || "-", a = kc(a, !1) || "-", n = ma("" + d + e + f + j + o + a), q = [];
q[k]("__utma=" + d);
q[k]("__utmb=" + e);
q[k]("__utmc=" + f);
q[k]("__utmx=" + j);
q[k]("__utmz=" + o);
q[k]("__utmv=" + a);
q[k]("__utmk=" + n);
d = q[y]("&");
if (!d) {
	return b;
}
e = b[m]("#");
return c ? e < 0 ? b + "#" + d : b + "&" + d : (c = "", f = b[m]("?"), e > 0 && (c = b[x](e), b = b[x](0, e)), f < 0 ? b + "?" + d + c : b + "&" + d + c);
};
var bd = "|", dd = function (a, b, c, d, e, f, j, o, n) {
var q = cd(a, b);
q || (q = {}, a.get($a)[k](q));
q.id_ = b;
q.affiliation_ = c;
q.total_ = d;
q.tax_ = e;
q.shipping_ = f;
q.city_ = j;
q.state_ = o;
q.country_ = n;
q.items_ = [];
return q;
}, ed = function (a, b, c, d, e, f, j) {
var a = cd(a, b) || dd(a, b, "", 0, 0, 0, "", "", ""), o;
a:
{if (a && a.items_) {
	o = a.items_;
	for (var n = 0; n < o[r]; n++) {
		if (o[n].sku_ == c) {
			o = o[n];
			break a;
		}
	}
}
o = h;
}
n = o || {};
n.transId_ = b;
n.sku_ = c;
n.name_ = d;
n.category_ = e;
n.price_ = f;
n.quantity_ = j;
o || a.items_[k](n);
return n;
}, cd = function (a, b) {
for (var c = a.get($a), d = 0; d < c[r]; d++) {
if (c[d].id_ == b) {
	return c[d];
}
}
return h;
};
var fd, gd = function (a) {
var f;
var e;
if (!fd) {
var b;
b = G[v].hash;
var c = U[ga], d = /^#?gaso=([^&]*)/;
if (f = (e = (b = b && b[fa](d) || c && c[fa](d)) ? b[1] : qa(V("GASO")), b = e) && b[fa](/^(?:\|([-0-9a-z.]{1,30})\|)?([-.\w]{10,1200})$/i), c = f) {
	if (Mc(a, "GASO", "" + b), Z._gasoDomain = a.get(J), Z._gasoCPath = a.get(L), b = "https://" + ((c[1] || "www") + ".google.com") + "/analytics/reporting/overlay_js?gaso=" + c[2] + "&" + na()) {
		a = G.createElement("script"), a.type = "text/javascript", a.async = !0, a.src = b, a.id = "_gasojs", a.onload = g, b = G.getElementsByTagName("script")[0], b.parentNode.insertBefore(a, b);
	}
}
fd = !0;
}
};
var kd = function (a, b) {
if (a.b(N, 0) % 100 >= a.b(Wb, 0)) {
return !1;
}
var c = hd();
c == g && (c = id());
if (c == g || c == Infinity || isNaN(c)) {
return !1;
}
c > 0 ? b(jd(c)) : pa(U, "load", function () {
kd(a, b);
}, !1);
return !0;
}, jd = function (a) {
var b = new Fc, c = i.min(i.floor(a / 100), 5000);
b.e(14, 1, c > 0 ? c + "00" : "0");
b.j(14, 1, a);
return b;
}, hd = function () {
var a = U.performance || U.webkitPerformance;
return (a = a && a.timing) && a.loadEventStart - a.fetchStart;
}, id = function () {
if (U.top == U) {
var a = U.external, b = a && a.onloadT;
a && !a.isValidLoadTime && (b = g);
b > 2147483648 && (b = g);
b > 0 && a.setPageReadyTime();
return b;
}
};
var Q = function (a, b, c) {
function d(a) {
return function (b) {
	if ((b = b.get(Yb)[a]) && b[r]) {
		for (var c = $b(e, a), d = 0; d < b[r]; d++) {
			b[d].call(e, c);
		}
	}
};
}
var e = this;
this.a = new fc;
this.get = function (a) {
return this.a.get(a);
};
this.set = function (a, b, c) {
this.a.set(a, b, c);
};
this.set(ya, b || "UA-XXXXX-X");
this.set(Aa, a || "");
this.set(za, c || "");
this.set(I, i.round((new Date).getTime() / 1000));
this.set(L, "/");
this.set(Ba, 63072000000);
this.set(Da, 15768000000);
this.set(Ca, 1800000);
this.set(Ea, !1);
this.set(Wa, 50);
this.set(Fa, !1);
this.set(Ga, !0);
this.set(Ha, !0);
this.set(Ia, !0);
this.set(Ja, !0);
this.set(Ka, !0);
this.set(Ma, "utm_campaign");
this.set(La, "utm_id");
this.set(Na, "gclid");
this.set(Oa, "utm_source");
this.set(Pa, "utm_medium");
this.set(Qa, "utm_term");
this.set(Ra, "utm_content");
this.set(Sa, "utm_nooverride");
this.set(Ta, 100);
this.set(Wb, 10);
this.set(Ua, "/__utm.gif");
this.set(Va, 1);
this.set($a, []);
this.set(M, []);
this.set(Xa, Qc);
this.set(Ya, []);
this.set(Za, []);
this.r("auto");
this.set(eb, G.referrer);
this.set(Yb, {hit:[], load:[]});
this.a.h("0", $c);
this.a.h("1", Pc);
this.a.h("2", Xc);
this.a.h("4", d("load"));
this.a.h("5", gd);
this.a.d("A", rc);
this.a.d("B", tc);
this.a.d("C", Pc);
this.a.d("D", qc);
this.a.d("E", bc);
this.a.d("F", md);
this.a.d("G", Nc);
this.a.d("H", uc);
this.a.d("I", Bc);
this.a.d("J", Ic);
this.a.d("K", d("hit"));
this.a.d("L", nd);
this.a.d("M", od);
this.get(I) === 0 && D(111);
this.a.G();
};
A = Q[s];
A.g = function () {
var a = this.get(ab);
a || (a = new Fc, this.set(ab, a));
return a;
};
A.oa = function (a) {
for (var b in a) {
var c = a[b];
a.hasOwnProperty(b) && typeof c != "function" && this.set(b, c, !0);
}
};
A.ka = function (a) {
a && a != g && (a.constructor + "")[m]("String") > -1 ? (D(13), this.set(cb, a, !0)) : typeof a === "object" && a !== h && this.oa(a);
this.a.f("page");
};
A.t = function (a, b, c, d) {
if (a == "" || !Dc(a) || b == "" || !Dc(b)) {
return !1;
}
if (c != g && !Dc(c)) {
return !1;
}
if (d != g && !Ec(d)) {
return !1;
}
this.set(Nb, a, !0);
this.set(Ob, b, !0);
this.set(Pb, c, !0);
this.set(Qb, d, !0);
this.a.f("event");
return !0;
};
A.la = function (a, b, c, d) {
if (!a || !b) {
return !1;
}
this.set(Rb, a[x](0, 15), !0);
this.set(Ub, b[x](0, 15), !0);
this.set(Vb, c || G[v].href, !0);
d && this.set(cb, d, !0);
this.a.f("social");
return !0;
};
A.ja = function () {
var a = this;
return kd(this.a, function (b) {
a.s(b);
});
};
A.ma = function () {
this.a.f("trans");
};
A.s = function (a) {
this.set(bb, a, !0);
this.a.f("event");
};
A.S = function (a) {
this.l();
var b = this;
return {_trackEvent:function (c, d, e) {
D(91);
b.t(a, c, d, e);
}};
};
A.V = function (a) {
return this.get(a);
};
A.da = function (a, b) {
if (a) {
if (a != g && (a.constructor + "")[m]("String") > -1) {
	this.set(a, b);
} else {
	if (typeof a == "object") {
		for (var c in a) {
			a.hasOwnProperty(c) && this.set(c, a[c]);
		}
	}
}
}
};
A.addEventListener = function (a, b) {
var c = this.get(Yb)[a];
c && c[k](b);
};
A.removeEventListener = function (a, b) {
for (var c = this.get(Yb)[a], d = 0; c && d < c[r]; d++) {
if (c[d] == b) {
	c.splice(d, 1);
	break;
}
}
};
A.X = function () {
return "5.1.0";
};
A.r = function (a) {
this.get(Ga);
a = a == "auto" ? ta(G.domain) : !a || a == "-" || a == "none" ? "" : a[z]();
this.set(J, a);
};
A.ba = function (a) {
this.set(Ga, !!a);
};
A.W = function (a, b) {
return ad(this.a, a, b);
};
A.link = function (a, b) {
if (this.a.get(Ea) && a) {
var c = ad(this.a, a, b);
G[v].href = c;
}
};
A.aa = function (a, b) {
this.a.get(Ea) && a && a.action && (a.action = ad(this.a, a.action, b));
};
A.ea = function () {
this.l();
var a = this.a, b = G.getElementById ? G.getElementById("utmtrans") : G.utmform && G.utmform.utmtrans ? G.utmform.utmtrans : h;
if (b && b[ea]) {
a.set($a, []);
for (var b = b[ea][t]("UTM:"), c = 0; c < b[r]; c++) {
	b[c] = la(b[c]);
	for (var d = b[c][t](bd), e = 0; e < d[r]; e++) {
		d[e] = la(d[e]);
	}
	"T" == d[0] ? dd(a, d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8]) : "I" == d[0] && ed(a, d[1], d[2], d[3], d[4], d[5], d[6]);
}
}
};
A.L = function (a, b, c, d, e, f, j, o) {
return dd(this.a, a, b, c, d, e, f, j, o);
};
A.J = function (a, b, c, d, e, f) {
return ed(this.a, a, b, c, d, e, f);
};
A.fa = function (a) {
bd = a || "|";
};
A.ca = function (a, b, c, d) {
var e = this.a;
if (a <= 0 || a > e.get(Wa)) {
a = !1;
} else {
if (!b || !c || C(b)[r] + C(c)[r] > 64) {
	a = !1;
} else {
	d != 1 && d != 2 && (d = 3);
	var f = {};
	ca(f, b);
	f.value = c;
	f.scope = d;
	e.get(M)[a] = f;
	a = !0;
}
}
a && this.a.i();
return a;
};
A.U = function (a) {
this.a.get(M)[a] = g;
this.a.i();
};
A.Y = function (a) {
return (a = this.a.get(M)[a]) && a[ia] == 1 ? a[ea] : g;
};
A.ha = function (a, b, c) {
this.g().e(a, b, c);
};
A.ia = function (a, b, c) {
this.g().j(a, b, c);
};
A.Z = function (a, b) {
return this.g().w(a, b);
};
A.$ = function (a, b) {
return this.g().z(a, b);
};
A.P = function (a) {
this.g().u(a);
};
A.Q = function (a) {
this.g().v(a);
};
A.T = function () {
return new Fc;
};
A.H = function (a) {
a && this.get(Ya)[k](a[z]());
};
A.M = function () {
this.set(Ya, []);
};
A.I = function (a) {
a && this.get(Za)[k](a[z]());
};
A.N = function () {
this.set(Za, []);
};
A.K = function (a, b, c) {
if (a && b) {
var d = this.get(Xa);
d.splice(c ? 0 : d[r], 0, a + ":" + b[z]());
}
};
A.O = function () {
this.set(Xa, []);
};
A.R = function (a) {
this.a[da]();
var b = this.get(L), c = qa(V("__utmx")) || "";
this.set(L, a);
this.a.i();
Mc(this.a, "__utmx", c);
this.set(L, b);
};
A.l = function () {
this.a[da]();
};
A.ga = function (a) {
a && a != "" && (this.set(nb, a), this.a.f("var"));
};
var md = function (a) {
a.get(Lb) !== "trans" && a.b(xb, 0) >= 500 && a[u]();
if (a.get(Lb) === "event") {
var b = (new Date).getTime(), c = a.b(yb, 0), d = a.b(tb, 0), c = i.floor(0.2 * ((b - (c != d ? c : c * 1000)) / 1000));
c > 0 && (a.set(yb, b), a.set(O, i.min(10, a.b(O, 0) + c)));
a.b(O, 0) <= 0 && a[u]();
}
}, od = function (a) {
a.get(Lb) === "event" && a.set(O, i.max(0, a.b(O, 10) - 1));
};
var pd = function () {
var a = [];
this.add = function (b, c, d) {
d && (c = C("" + c));
a[k](b + "=" + c);
};
this.toString = function () {
return a[y]("&");
};
}, qd = function (a, b) {
(b || a.get(Va) != 2) && a.m(xb);
}, rd = function (a, b) {
b.add("utmwv", "5.1.0");
b.add("utms", a.get(xb));
b.add("utmn", na());
var c = G[v].hostname;
B(c) || b.add("utmhn", c, !0);
c = a.get(Ta);
c != 100 && b.add("utmsp", c, !0);
}, td = function (a, b) {
b.add("utmac", a.get(ya));
sd(a, b);
Z.o && b.add("aip", 1);
b.add("utmu", vc.va());
}, sd = function (a, b) {
function c(a, b) {
b && d[k](a + "=" + b + ";");
}
var d = [];
c("__utma", hc(a));
c("__utmz", nc(a, !1));
c("__utmv", kc(a, !0));
c("__utmx", qa(V("__utmx")));
b.add("utmcc", d[y]("+"), !0);
}, ud = function (a, b) {
a.get(Ha) && (b.add("utmcs", a.get(lb), !0), b.add("utmsr", a.get(gb)), b.add("utmsc", a.get(hb)), b.add("utmul", a.get(kb)), b.add("utmje", a.get(ib)), b.add("utmfl", a.get(jb), !0));
}, vd = function (a, b) {
a.get(Ka) && a.get(db) && b.add("utmdt", a.get(db), !0);
b.add("utmhid", a.get(fb));
b.add("utmr", wa(a.get(eb), a.get(L)), !0);
b.add("utmp", C(a.get(cb), !0), !0);
}, wd = function (a, b) {
for (var c = a.get(ab), d = a.get(bb), e = a.get(M) || [], f = 0; f < e[r]; f++) {
var j = e[f];
j && (c || (c = new Fc), c.e(8, f, j[ga]), c.e(9, f, j[ea]), j[ia] != 3 && c.e(11, f, "" + j[ia]));
}
!B(a.get(Nb)) && !B(a.get(Ob)) && (c || (c = new Fc), c.e(5, 1, a.get(Nb)), c.e(5, 2, a.get(Ob)), e = a.get(Pb), e != g && c.e(5, 3, e), e = a.get(Qb), e != g && c.j(5, 1, e));
c ? b.add("utme", c.pa(d), !0) : d && b.add("utme", d.n(), !0);
}, xd = function (a, b, c) {
var d = new pd;
qd(a, c);
rd(a, d);
d.add("utmt", "tran");
d.add("utmtid", b.id_, !0);
d.add("utmtst", b.affiliation_, !0);
d.add("utmtto", b.total_, !0);
d.add("utmttx", b.tax_, !0);
d.add("utmtsp", b.shipping_, !0);
d.add("utmtci", b.city_, !0);
d.add("utmtrg", b.state_, !0);
d.add("utmtco", b.country_, !0);
!c && td(a, d);
return d[p]();
}, yd = function (a, b, c) {
var d = new pd;
qd(a, c);
rd(a, d);
d.add("utmt", "item");
d.add("utmtid", b.transId_, !0);
d.add("utmipc", b.sku_, !0);
d.add("utmipn", b.name_, !0);
d.add("utmiva", b.category_, !0);
d.add("utmipr", b.price_, !0);
d.add("utmiqt", b.quantity_, !0);
!c && td(a, d);
return d[p]();
}, zd = function (a, b) {
var c = a.get(Lb);
if (c == "page") {
c = new pd, qd(a, b), rd(a, c), wd(a, c), ud(a, c), vd(a, c), !b && td(a, c), c = [c[p]()];
} else {
if (c == "event") {
	c = new pd, qd(a, b), rd(a, c), c.add("utmt", "event"), wd(a, c), ud(a, c), vd(a, c), !b && td(a, c), c = [c[p]()];
} else {
	if (c == "var") {
		c = new pd, qd(a, b), rd(a, c), c.add("utmt", "var"), !b && td(a, c), c = [c[p]()];
	} else {
		if (c == "trans") {
			for (var c = [], d = a.get($a), e = 0; e < d[r]; ++e) {
				c[k](xd(a, d[e], b));
				for (var f = d[e].items_, j = 0; j < f[r]; ++j) {
					c[k](yd(a, f[j], b));
				}
			}
		} else {
			c == "social" ? b ? c = [] : (c = new pd, qd(a, b), rd(a, c), c.add("utmt", "social"), c.add("utmsn", a.get(Rb), !0), c.add("utmsa", a.get(Ub), !0), c.add("utmsid", a.get(Vb), !0), wd(a, c), ud(a, c), vd(a, c), td(a, c), c = [c[p]()]) : c = [];
		}
	}
}
}
return c;
}, nd = function (a) {
var b, c = a.get(Mb), d = a.get(Va);
if (d == 0 || d == 2) {
var e = a.get(Ua) + "?";
b = zd(a, !0);
for (var f = 0, j = b[r]; f < j; f++) {
	Ad(b[f], d != 2 && f == j - 1 && c, e, !0);
}
}
if (d == 1 || d == 2) {
b = zd(a);
f = 0;
for (j = b[r]; f < j; f++) {
	try {
		Ad(b[f], f == j - 1 && c);
	}
	catch (o) {
		var d = a, e = o, n = new pd;
		n.add("err", e[ga]);
		n.add("max", e.message);
		n.add("len", e.D);
		n.add("utmwv", "5.1.0e");
		n.add("utmac", d.get(ya));
		n.add("utmn", na());
		Z.o && n.add("aip", 1);
		Ad(n[p]());
	}
}
}
};
var Bd = "https:" == G[v].protocol ? "https://ssl.google-analytics.com" : "http://www.google-analytics.com", Cd = function (a) {
ca(this, "len");
this.message = 8192;
this.D = a;
}, Dd = function (a) {
ca(this, "ff2post");
this.message = 2036;
this.D = a;
}, Ad = function (a, b, c, d) {
b = b || oa;
if (d || a[r] <= 2036) {
Ed(a, b, c);
} else {
if (a[r] <= 8192) {
	if (U[ja].userAgent[m]("Firefox") >= 0 && ![].reduce) {
		throw new Dd(a[r]);
	}
	Fd(a, b) || Gd(a, b);
} else {
	throw new Cd(a[r]);
}
}
}, Ed = function (a, b, c) {
var c = c || Bd + "/__utm.gif?", d = new Image(1, 1);
d.src = c + a;
d.onload = function () {
d.onload = h;
b();
};
}, Fd = function (a, b) {
var c, d = Bd + "/p/__utm.gif", e = U.XDomainRequest;
if (e) {
c = new e, c.open("POST", d);
} else {
if (e = U.XMLHttpRequest) {
	e = new e, "withCredentials" in e && (c = e, c.open("POST", d, !0), c.setRequestHeader("Content-Type", "text/plain"));
}
}
if (c) {
return c.onreadystatechange = function () {
	c.readyState == 4 && (b(), c = h);
}, c.send(a), !0;
}
}, Gd = function (a, b) {
if (G.body) {
a = aa(a);
try {
	var c = G.createElement("<iframe name=\"" + a + "\"></iframe>");
}
catch (d) {
	c = G.createElement("iframe"), ca(c, a);
}
c.height = "0";
c.width = "0";
c.style.display = "none";
c.style.visibility = "hidden";
var e = G[v], e = Bd + "/u/post_iframe.html#" + aa(e.protocol + "//" + e[ha] + "/favicon.ico"), f = function () {
	c.src = "";
	c.parentNode && c.parentNode.removeChild(c);
};
pa(U, "beforeunload", f);
var j = !1, o = 0, n = function () {
	if (!j) {
		try {
			if (o > 9 || c.contentWindow[v][ha] == G[v][ha]) {
				j = !0;
				f();
				var a = U;
				a.removeEventListener ? a.removeEventListener("beforeunload", f, !1) : a.detachEvent && a.detachEvent("onbeforeunload", f);
				b();
				return;
			}
		}
		catch (d) {
		}
		o++;
		setTimeout(n, 200);
	}
};
pa(c, "load", n);
G.body.appendChild(c);
c.src = e;
} else {
wc(function () {
	Gd(a, b);
}, 100);
}
};
var $ = function () {
this.o = !1;
this.A = {};
this.ra = 0;
this._gasoCPath = this._gasoDomain = g;
P($[s], "_createTracker", $[s].k, 55);
P($[s], "_getTracker", $[s].ta, 0);
P($[s], "_getTrackerByName", $[s].p, 51);
P($[s], "_anonymizeIp", $[s].sa, 16);
Zb();
};
$[s].ta = function (a, b) {
return this.k(a, g, b);
};
$[s].k = function (a, b, c) {
b && D(23);
c && D(67);
b == g && (b = "~" + Z.ra++);
return Z.A[b] = new Q(b, a, c);
};
$[s].p = function (a) {
a = a || "";
return Z.A[a] || Z.k(g, a);
};
$[s].sa = function () {
this.o = !0;
};
var Z = new $;
var Hd = U._gat;
Hd && typeof Hd._getTracker == "function" ? Z = Hd : U._gat = Z;
var Cc = new Y;
a:
{var Id = U._gaq, Jd = !1;
if (Id && typeof Id[k] == "function" && (Jd = Object[s][p].call(Object(Id)) == "[object Array]", !Jd)) {
Cc = Id;
break a;
}
U._gaq = Cc;
Jd && Cc[k].apply(Cc, Id);
}
})();

