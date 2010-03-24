(add-hook
  'nxml-mode-hook
  (lambda ()
    (setq rng-schema-locating-files-default
          (append '("/var/www/dev.xboo.st/webroot/docbook/locatingrules.xml")
                  rng-schema-locating-files-default ))))
